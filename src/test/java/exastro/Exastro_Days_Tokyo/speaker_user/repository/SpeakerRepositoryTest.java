/*   Copyright 2021 NEC Corporation
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package exastro.Exastro_Days_Tokyo.speaker_user.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class SpeakerRepositoryTest {
	
	private MockRestServiceServer mockServer;
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	protected RestTemplate restTemplate;
	
	@Autowired
	private SpeakerRepository speakerRepository;
	
	@Test
	public void test_getSpeakerList() throws JsonProcessingException, ParseException {
		
		// Mock設定
		List<Integer> speakerIdList = new ArrayList<>();
		mockServer = MockRestServiceServer.createServer(restTemplate);
		mockServer.expect(requestTo("http://localhost:8080" + "/api/v1/speaker"))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(mapper.writeValueAsString(getSpeakerListMock0()), MediaType.APPLICATION_JSON));
		
		// 対象メソッド実行
		List<String> speakerList = speakerRepository.getSpeakerList(speakerIdList);
		
		// 以下、結果確認
		assertThat(speakerList.isEmpty());
		
		// Mock設定２
		speakerIdList.add(1);
		speakerIdList.add(2);
		speakerIdList.add(3);
		mockServer = MockRestServiceServer.createServer(restTemplate);
		String query_param = "?" + String.join("&", speakerIdList.stream().map(e->"speaker_id=" + e.toString()).toArray(String[]::new));
		mockServer.expect(requestTo("http://localhost:8080" + "/api/v1/speaker" + query_param))
				.andRespond(withSuccess(getSpeakerListMock3_json(), MediaType.APPLICATION_JSON));
		
		// 対象メソッド実行
		speakerList = speakerRepository.getSpeakerList(speakerIdList);
		
		// 以下、結果確認
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		assertThat(speakerList).hasSize(3);
		
		assertThat(speakerList.get(0), is("item1"));
		assertThat(speakerList.get(1), is("item2"));
		assertThat(speakerList.get(2), is("item3"));
		
		mockServer.verify();
	}
	
	// Test Data
	private String[] getSpeakerListMock0() {
		
		String[] testData = new String[0];
		
		return testData;
	}
	
	private String getSpeakerListMock3_json() {
		
		return "[ \"item1\", \"item2\", \"item3\"]";
	}
}
