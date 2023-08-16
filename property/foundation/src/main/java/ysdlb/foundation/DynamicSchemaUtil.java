package ysdlb.foundation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.platform.commons.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhwu
 * @date 2022/2/21 16:05
 */
public class DynamicSchemaUtil {


    private static final String REG_1 = "(\\$\\{\\{([a-zA-z.]*)}})+";
    private static final String REG_2 = "([a-zA-Z.]+)";

    private final static Pattern PATTERN_1 = Pattern.compile(REG_1);
    private final static Pattern PATTERN_2 = Pattern.compile(REG_2);


    public static String format(String jsonStr, JSONObject data) {
        String result = jsonStr;
        if (StringUtils.isBlank(jsonStr)) {
            return result;
        }
        Matcher matcher = PATTERN_1.matcher(jsonStr);
        while (matcher.find()) {
            String str = jsonStr.substring(matcher.start(), matcher.end());
            System.out.println(str);
            Matcher matcher2 = PATTERN_2.matcher(str);
            if (matcher2.find()) {
                String fieldName = str.substring(matcher2.start(), matcher2.end());
                Object targetValue = replace(fieldName, data);
                result = result.replace(str, JSON.toJSONString(targetValue));
            }
        }
        return result;
    }

    private static Object replace(String fieldName, JSONObject jsonObject) {
        String[] filedSplit = CompareUtil.split(fieldName);
        JSONObject lastObject = jsonObject;
        for (int i = 0; i <= filedSplit.length - 1; i++) {
            String field = CompareUtil.replaceArray(filedSplit[i]);
            Object currentObject = lastObject.get(field);
            if (i != filedSplit.length - 1) {
                if (CompareUtil.isJsonObject(currentObject)) {
                    //jsonobject，说明当前不是最底层字段
                    lastObject = lastObject.getJSONObject(field);
                }
                continue;
            }
            return currentObject;
        }
        return null;
    }


    public static void main(String[] args) {
        String config = """
                        {
                            "criteria": {
                                "type": "ALL"
                            },
                            "templateId": "stardash-information",
                            "reservationSystemAvailableDateTime": "",
                            "orsActivityId": "1",
                            "platforms": [
                                "app",
                                "WeChat",
                                "web"
                            ],
                            "giftExchangeEndDateTime": "",
                            "reservationId": "1",
                            "achievedUrl": "https://artwork-assets-staging-sbux.starbucks.com.cn/mobile/popup/index.html?crmId=CRE20230329C00000018&r=starTask",
                            "launchEndDateTime": "2023-04-22T07:28:26.000Z",
                            "tasks": [
                                {
                                    "giftWithdrawMethod": "EXCHANGE",
                                    "ruleNameEn": "1",
                                    "progressAccuracy": "0",
                                    "rewardNameEn": "1",
                                    "reservationNotifyProductCode": "progress",
                                    "reminderAmount": 0,
                                    "ruleNameZh": "1",
                                    "rewardType": "STAR",
                                    "rewardNameZh": "1",
                                    "reservationCancelProductCode": "progress",
                                    "thresholdValue": 0,
                                    "distributeMethod": "SEND_DIRECTLY",
                                    "specifyProductType": "NOTPRODUCT",
                                    "totalAmount": 1,
                                    "rewardDescriptionEn": "送星星",
                                    "customDescription": "送星星",
                                    "progressUnit": "2",
                                    "rewardDescription": "送星星",
                                    "rewardImage": "1",
                                    "totalRewardImage": "https://active.stg.starbucks.com.cn/starcrush/d05d240a-d48e-4590-a9d0-d770699dc8cd.webp",
                                    "attributeName": "progress",
                                    "totalQualificationSize": 1,
                                    "rewards": [],
                                    "task": [
                                        {}
                                    ]
                                }
                            ],
                            "miniStarDash": false,
                            "rewardExpireDateTime": "2023-04-28T07:28:35.000Z",
                            "reservationEndDateTime": "",
                            "pageId": {
                                "popup": "acb52332-d6b7-48ce-aaba-555f188b057d",
                                "cardfeed": "d1fbffc8-0214-4f3b-96da-7857e1b2b502",
                                "detail": "52c0fbe2-7505-4a27-b9bc-aefbd59d1e0b"
                            },
                            "crmId": "CRE20230329C00000018",
                            "versions": [
                                {
                                    "comparator": "gte",
                                    "phoneType": "ios",
                                    "value": ""
                                },
                                {
                                    "comparator": "gte",
                                    "phoneType": "android",
                                    "value": ""
                                }
                            ],
                            "sysChannel": "CRE",
                            "crmName": "测试-积星类-升玉14",
                            "status": "ACTIVE",
                            "launchStartDateTimeAAAlaunchEndDateTime": [
                                "2023-03-29T07:28:22.000Z",
                                "2023-04-22T07:28:26.000Z"
                            ],
                            "inventorySwitch": false,
                            "singleRewardForMilestone": false,
                            "taskType": "SINGLE_TASK",
                            "detailPageUrl": "https://artwork-assets-staging-sbux.starbucks.com.cn/mobile/starTask/index.html?crmId=CRE20230329C00000018",
                            "launchStartDateTime": "2023-03-29T07:28:22.000Z",
                            "enterForEndDateTime": "",
                            "usePromoStatus": false,
                            "ruleType": "SINGLE_SKU",
                            "wechatUrl": "/shares/regionalStardash/pages/starTask/detail?crmId=CRE20230329C00000018",
                            "singleSku": {},
                            "cardFeedRatio": "2:1",
                            "enterForStartDateTime": "",
                            "implementationType": "WEB",
                            "maVersions": "2.0",
                            "displayNameZh": "测试",
                            "reservationSystemAvailableDateTimeAAAreservationEndDateTime": [
                                "",
                                ""
                            ],
                            "displayNameEn": "test",
                            "enterForStartDateTimeAAAenterForEndDateTime": [
                                "",
                                ""
                            ],
                            "cardFeedUrl": "https://artwork-assets-staging-sbux.starbucks.com.cn/mobile/starTask/index.html?crmId=CRE20230329C00000018&r=cardfeed"
                        }
                """;
        JSONObject jsonObject = JSONObject.parseObject(config);

        String template = "{\"data\":{\"page\":{\"pageName\":\"starTaskInformation\",\"pageType\":2,\"remarks\":\"\"},\"widgets\":[{\"id\":\"00000001\",\"sequence\":0,\"parent\":\"page\",\"config\":{\"type\":\"tasks\",\"content\":${{tasks.task}}}}]},\"description\":\"PAGE_STARDASH\",\"page\":\"PAGE_STARDASH\",\"title\":\"PAGE_STARDASH\"}";

        System.out.println(format(template, jsonObject));
    }
}
