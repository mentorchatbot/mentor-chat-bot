package com.chatbot.mentor.service;

import com.chatbot.mentor.dto.JobInfoResponseDto;
import com.chatbot.mentor.util.MentorUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author junho.park
 */
public class ExternalApiServiceMockTest {
    private static final String JSON_RESULT_MOCK = "{\n" +
            "    \"dataSearch\": {\n" +
            "        \"content\": [\n" +
            "            {\n" +
            "                \"profession\": \"관리직(임원·부서장)\",\n" +
            "                \"summary\": \"- 교장은 초등학교나 중학교, 고등학교, 특수학교를 대표하는 감독 책임자로서 학교의 교육, 행정 및 기타 운영활동에 관련된 모든 제반 사항을 기획하고 조정하는 일을 담당한다.\\n- 학교의 교육목적을 효율적으로 달성하기 위한 교육 활동들을 계획하고 학급의 규모를 결정하며 교사와 강사들의 교육활동을 기획지휘한다.\\n- 학교의 행정 및 기타 운영 활동들과 관련된 사항들을 감독한다. \\n- 학교의 예산을 집행하고, 교사 및 기타 직원의 모집과 채용에 관여하는 등 인사 행정에 관련한 일을 한다.\",\n" +
            "                \"similarJob\": \"null\",\n" +
            "                \"salery\": \"4000 만원 이상\",\n" +
            "                \"jobdicSeq\": \"481\",\n" +
            "                \"equalemployment\": \"매우좋음\",\n" +
            "                \"totalCount\": \"7\",\n" +
            "                \"aptd_type_code\": \"104737\",\n" +
            "                \"prospect\": \"보통미만\",\n" +
            "                \"job_ctg_code\": \"100041\",\n" +
            "                \"job_code\": \"100013\",\n" +
            "                \"job\": \"교장\",\n" +
            "                \"possibility\": \"보통이상\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"profession\": \"관리직(임원·부서장)\",\n" +
            "                \"summary\": \"- 국회의원은 선거를 통해 선출된 국민의 대표로서 국회에서 헌법과 법률의 개정 및 의결과 관련된 일을 하고, 정부 예산안을 심의확정하는 등의 업무를 담당한다. \\r\\n\\r\\n- 헌법과 법률에 규정된 개정절차에 따라 특정 조항을 수정, 삭제하거나 새로운 조항을 추가한다. \\r\\n\\r\\n- 국가나 국민에게 중대한 재정적 부담을 지우는 조약 또는 입법사항에 관한 조약의 체결, 비준에 대한 동의권을 행사한다. \\r\\n\\r\\n- 정부의 예산안에 대한 심의와 수정을 통해 예산안을 확정하며, 국가의 수입 및 지출에 대한 결산을 심사한다. \\r\\n\\r\\n- 국정감사와 조사를 통해 국정이 법에 따라 잘 운영되고 있는지를 감시하고 잘못된 부분을 적발하여 시정하도록 한다.\",\n" +
            "                \"similarJob\": \"정치인,정당인,대통령\",\n" +
            "                \"salery\": \"4000 만원 이상\",\n" +
            "                \"jobdicSeq\": \"1061\",\n" +
            "                \"equalemployment\": \"보통이상\",\n" +
            "                \"totalCount\": \"7\",\n" +
            "                \"aptd_type_code\": \"104759\",\n" +
            "                \"prospect\": \"보통미만\",\n" +
            "                \"job_ctg_code\": \"100041\",\n" +
            "                \"job_code\": \"100013\",\n" +
            "                \"job\": \"국회의원\",\n" +
            "                \"possibility\": \"보통이상\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"profession\": \"관리직(임원·부서장)\",\n" +
            "                \"summary\": \"- 기업고위임원은 기업을 대표하며, 사업체 경영의 능률과 경제성 제고, 이익의 극대화를 위한 조사연구나 홍보, 판매 등을 계획하고 관리한다.\\n- 이사회나 이와 유사한 운영기관 또는 법령에 의해 위임된 권한 하에 사업체의 전반적인 정책을 수립하고 운영현황, 과거의 실적, 미래의 계획을 평가하여 사업계획을 결정한다.\\n- 대외적으로 사업체를 대표하며, 고위 간부와 임원의 임명을 결정하고 예산 배정과 조직의 업무분장을 조정하고 승인한다. \\n- 부서간의 활동을 조정하고 사업체 운영상의 문제점을 해결한다.\",\n" +
            "                \"similarJob\": \"조직관리자\",\n" +
            "                \"salery\": \"4000 만원 이상\",\n" +
            "                \"jobdicSeq\": \"238\",\n" +
            "                \"equalemployment\": \"보통미만\",\n" +
            "                \"totalCount\": \"7\",\n" +
            "                \"aptd_type_code\": \"null\",\n" +
            "                \"prospect\": \"보통미만\",\n" +
            "                \"job_ctg_code\": \"100041\",\n" +
            "                \"job_code\": \"100007\",\n" +
            "                \"job\": \"기업고위임원\",\n" +
            "                \"possibility\": \"보통미만\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"profession\": \"관리직(임원·부서장)\",\n" +
            "                \"summary\": \"- 스포츠에이전트는 스포츠 관련 프로그램 및 서비스를 개발하고 운영을 위한 기획 및 절차를 조직하여 조정관리한다.\\n- 시합이나 경기에 관한 정보를 수집하고 훈련과정을 설계하기도 하며 스포츠 관련 회사와 소속선수를 연결하여 계약을 성사시킨다. \\n- 선수를 돌보고 스케줄 관리를 하는 것은 물론 선수의 이미지관리, 운동량 관리, 광고계약 체결, 연봉 협상, 언론 홍보, 팬클럽 관리 등을 한다. \\n- 선수의 잠재능력을 파악해 상품 가치를 높여주며 계약을 맺은 선수의 훈련 프로그램과 의료혜택지원, 법률 서비스를 지원한다.\",\n" +
            "                \"similarJob\": \"스포츠마케터,스포츠마케팅전문가\",\n" +
            "                \"salery\": \"4000 만원 이상\",\n" +
            "                \"jobdicSeq\": \"1088\",\n" +
            "                \"equalemployment\": \"매우좋음\",\n" +
            "                \"totalCount\": \"7\",\n" +
            "                \"aptd_type_code\": \"104748\",\n" +
            "                \"prospect\": \"매우좋음\",\n" +
            "                \"job_ctg_code\": \"100041\",\n" +
            "                \"job_code\": \"100007\",\n" +
            "                \"job\": \"스포츠에이전트\",\n" +
            "                \"possibility\": \"보통이상\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"profession\": \"관리직(임원·부서장)\",\n" +
            "                \"summary\": \"- 외교관은 본국을 대표하여 외국에 파견되어 외국과의 교섭을 통해 정치, 경제, 상업적 이익을 보호, 증진을 추구하며, 해외동포와 해외여행을 하는 자국민을 보호한다. \\r\\n- 부임한 나라의 정치적 사건과 상황을 본국에 보고하며, 경제통상정보, 생활정보 등을 수집, 분석하여 본국의 정부나 기업에 알린다. \\r\\n- 본국을 대신해 본국의 이익과 정책을 옹호하는 교섭을 하고, 본국의 전통 및 문화를 알리는 문화홍보활동을 한다.\\r\\n- 본국과 주재국과의 우호관계를 증진시키고, 양국간의 경제적, 문화적, 과학적 관계를 발전시킨다.\\r\\n- 자국을 대신해 주재국에 대하여 항의하기도 하고, 주재국에 자국의 정책을 소개한다.\\r\\n- 부임한 나라에 있는 자국민에게 각종 증명서를 발급해주고, 출생 및 혼인신고, 여권 발급과 연장 등의 업무와 자국민이 위험에 처해있을 때 그들을 보호한다.\",\n" +
            "                \"similarJob\": \"null\",\n" +
            "                \"salery\": \"4000 만원 이상\",\n" +
            "                \"jobdicSeq\": \"233\",\n" +
            "                \"equalemployment\": \"보통이상\",\n" +
            "                \"totalCount\": \"7\",\n" +
            "                \"aptd_type_code\": \"104759\",\n" +
            "                \"prospect\": \"보통미만\",\n" +
            "                \"job_ctg_code\": \"100041\",\n" +
            "                \"job_code\": \"100007\",\n" +
            "                \"job\": \"외교관\",\n" +
            "                \"possibility\": \"보통이상\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"profession\": \"관리직(임원·부서장)\",\n" +
            "                \"summary\": \"- 행정부고위공무원은 정부의 정책을 결정하고 예산 및 법령안을 작성ㆍ수정하며, 외국에서 국가를 대표ㆍ대리한다. \\r\\n\\r\\n- 정부의 정책을 결정하고 예산 및 법령안을 작성, 수정하며 정부부처의 법령을 해석하고 적용한다. \\r\\n\\r\\n- 정책수행을 기획, 조직 및 통제하고 외국에서 국가를 대표ㆍ대리한다. \\r\\n\\r\\n- 정부부처의 관련 행정업무를 기획, 지휘, 조정한다. \\r\\n\\r\\n- 각급 지방자치단체의 구역, 조직 및 행정관리, 주민복지증진, 산업진흥과 지역개발 및 주민생활 환경의 설치, 관리 등 지방행정 분야의 정책수행을 기획, 조직 및 통제한다.\",\n" +
            "                \"similarJob\": \"국회의원,지방의회의원,외교관\",\n" +
            "                \"salery\": \"4000 만원 이상\",\n" +
            "                \"jobdicSeq\": \"1278\",\n" +
            "                \"equalemployment\": \"보통이상\",\n" +
            "                \"totalCount\": \"7\",\n" +
            "                \"aptd_type_code\": \"104759\",\n" +
            "                \"prospect\": \"보통미만\",\n" +
            "                \"job_ctg_code\": \"100041\",\n" +
            "                \"job_code\": \"100013\",\n" +
            "                \"job\": \"행정부고위공무원\",\n" +
            "                \"possibility\": \"보통이상\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"profession\": \"관리직(임원·부서장)\",\n" +
            "                \"summary\": \"- 호텔지배인은 객실 예약업무, 판매업무, 접객, 식당, 주방, 홍보 등 호텔에서 이루어지는 다양한 작업들이 원활히 운용될 수 있도록 각종 활동들을 계획하고, 종사원들의 업무를 종합적으로 관리감독한다. \\n- 객실, 연회부 등 각 부서 소속 종업원의 작업계획 수립을 통해 업무가 원활히 운영될 수 있도록 관리조정하고, 종업원에게 효과적인 작업방법을 교육하고 훈련시킨다. \\n- 객실, 연회부 등 해당업장의 매출증진을 위한 전략을 수립하고 서비스 향상 및 청결 유지를 관리감독하며 고객의 불편사항을 처리한다. \\n- 회의, 연회, 리셉션 및 기타 목적을 위한 시설 사용에 대한 고객의 요구 사항을 파악하여 맞춤 서비스를 제공하는 업무를 수행한다.\",\n" +
            "                \"similarJob\": \"null\",\n" +
            "                \"salery\": \"4000 만원 이상\",\n" +
            "                \"jobdicSeq\": \"795\",\n" +
            "                \"equalemployment\": \"매우좋음\",\n" +
            "                \"totalCount\": \"7\",\n" +
            "                \"aptd_type_code\": \"104747\",\n" +
            "                \"prospect\": \"매우좋음\",\n" +
            "                \"job_ctg_code\": \"100041\",\n" +
            "                \"job_code\": \"100013\",\n" +
            "                \"job\": \"호텔지배인\",\n" +
            "                \"possibility\": \"보통이상\"\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";
    @Test
    public void Job_API_JSON_을파싱한다() {
        List<JobInfoResponseDto> list = new ArrayList<>();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(JSON_RESULT_MOCK);
        JsonObject jsonObjectDataSearch = (JsonObject) jsonObject.get("dataSearch");
        JsonArray jsonArray = (JsonArray) jsonObjectDataSearch.get("content");

        MentorUtil.parseJsonArrayToJobInfoList(list, jsonArray);

        assertThat(list.get(0).getProfession()).isEqualTo("관리직(임원·부서장)");
        assertThat(list.get(0).getJob()).isEqualTo("교장");
        assertThat(list.get(1).getProfession()).isEqualTo("관리직(임원·부서장)");
        assertThat(list.get(1).getJob()).isEqualTo("국회의원");
    }
}