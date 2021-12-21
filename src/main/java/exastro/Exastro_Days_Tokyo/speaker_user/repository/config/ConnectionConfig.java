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

package exastro.Exastro_Days_Tokyo.speaker_user.repository.config;

import lombok.Getter;
import lombok.Setter;

public class ConnectionConfig {
	
	@Getter
	@Setter
	private String protocol;
	
	@Getter
	@Setter
	private String host;
	
	@Getter
	@Setter
	private String port;
	
	public String buildBaseUri() {
		
		String baseUri = String.format("%s://%s:%s", this.protocol, this.host, this.port);
		return baseUri;
	}
	
}
