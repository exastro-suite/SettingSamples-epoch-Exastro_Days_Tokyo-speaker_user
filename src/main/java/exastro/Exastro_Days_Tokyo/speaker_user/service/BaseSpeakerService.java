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

package exastro.Exastro_Days_Tokyo.speaker_user.service;

import org.springframework.beans.factory.annotation.Autowired;

import exastro.Exastro_Days_Tokyo.speaker_user.repository.SpeakerRepository;
import exastro.Exastro_Days_Tokyo.speaker_user.repository.vo.SpeakerDetailVO;
import exastro.Exastro_Days_Tokyo.speaker_user.service.dto.SpeakerDetailDto;

public abstract class BaseSpeakerService {
	
	@Autowired
	protected SpeakerRepository repository;
	
	public BaseSpeakerService() {
		
	}

	public SpeakerDetailDto getSpeakerDetail(int speakerId) {
		
		SpeakerDetailDto speakerInfo = null;
		
		try {
			SpeakerDetailVO spvo = repository.getSpeakerDetail(speakerId);
			if(spvo == null) {
				return null;
			}
			speakerInfo = new SpeakerDetailDto(spvo.getSpeakerId(), spvo.getSpeakerName(), spvo.getSpeakerProfile());
		}
		catch(Exception e) {
			throw e;
		}
		return speakerInfo;
	}

}
