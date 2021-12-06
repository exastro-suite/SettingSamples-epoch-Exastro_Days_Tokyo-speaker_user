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

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import exastro.Exastro_Days_Tokyo.speaker_user.repository.vo.SpeakerVO;

@ConfigurationProperties(prefix = "service.speaker")
@Repository
public class SpeakerRepository extends BaseRepository {
	
	public SpeakerRepository(RestTemplate restTemplate) {
		super(restTemplate);
	}

	public List<SpeakerVO> getSpeakerList(List<Integer> speakerIdList) {
		
		String apiPath = "/api/v1/speaker";
		String apiUrl = buildBaseUri() + apiPath;
		
		SpeakerVO[] resBody = null;
		try {
			
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl);
			
			String uri = builder.queryParam("speaker", speakerIdList).toUriString();
			
			RequestEntity<Void> requestEntity = RequestEntity.get(new URI(uri)).build();
			
			ResponseEntity<SpeakerVO[]> response = restTemplate.exchange(requestEntity, SpeakerVO[].class);
			resBody = response.getBody();
		}
		catch(Exception e) {
			// TODO
			//throw e;
		}
		
		return Arrays.asList(resBody);
	}

}
