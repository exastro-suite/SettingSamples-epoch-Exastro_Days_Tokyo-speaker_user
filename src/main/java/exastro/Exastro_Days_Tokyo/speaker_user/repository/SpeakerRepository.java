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

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import exastro.Exastro_Days_Tokyo.speaker_user.repository.config.ConnectionConfig;
import exastro.Exastro_Days_Tokyo.speaker_user.repository.vo.SpeakerDetailVO;

@Repository
public class SpeakerRepository extends BaseRepository {
	
	@Autowired
	public SpeakerRepository(@Qualifier("configSpeaker") ConnectionConfig connectionConfig,
			RestTemplate restTemplate) {
		this.connectionConfig = connectionConfig;
		this.restTemplate = restTemplate;
	}

	public List<String> getSpeakerList(List<Integer> speakerIdList) {
		
		logger.debug("method called. [ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ]");
		
		String apiPath = "/api/v1/speaker";
		String apiUrl = connectionConfig.buildBaseUri() + apiPath;
		
		String[] resBody = null;
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl);
			
			if( ! CollectionUtils.isEmpty(speakerIdList)) {
				builder.queryParam("speaker_id", speakerIdList);
			}
			String uri = builder.toUriString();
			
			resBody = restTemplate.getForObject(uri, String[].class);
			return Arrays.asList(resBody);
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	public SpeakerDetailVO getSpeakerDetail(int speakerId) {
		
		logger.debug("method called. [ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ]");
		
		String apiPath = "/api/v1/speaker/{speakerId}";
		String apiUrl = connectionConfig.buildBaseUri() + apiPath;
		
		SpeakerDetailVO resBody = null;
		try {			
			logger.debug("restTemplate.getForObject [apiUrl: " + apiUrl + "], [speakerId: " + speakerId + "]");
		
			resBody = restTemplate.getForObject(apiUrl, SpeakerDetailVO.class, speakerId);
			return resBody;
		}
		catch(Exception e) {
			throw e;
		}
	}

}
