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

package exastro.Exastro_Days_Tokyo.speaker_user.controller.api.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import exastro.Exastro_Days_Tokyo.speaker_user.controller.api.v1.form.SpeakerDetailForm;
import exastro.Exastro_Days_Tokyo.speaker_user.service.SpeakerService;
import exastro.Exastro_Days_Tokyo.speaker_user.service.dto.SpeakerDetailDto;

public class BaseSpeakerController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected SpeakerService service;
	
	public BaseSpeakerController() {
		
	}
	
	@GetMapping("/{speaker_id}")
	public SpeakerDetailForm SpeakerDetail(@PathVariable(value = "speaker_id") @Validated int speaker_id) {
		
		SpeakerDetailForm speakerDetail = null;
		
		try {
			SpeakerDetailDto speakerDetailDto = service.getSpeakerDetail(speaker_id);
			speakerDetail = new SpeakerDetailForm();
		}
		catch(Exception e) {
			logger.debug(e.getMessage(), e);
			throw e;
		}
		
		return speakerDetail;
	}

}
