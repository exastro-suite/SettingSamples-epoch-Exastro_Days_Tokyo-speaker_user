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

package exastro.Exastro_Days_Tokyo.speaker_user.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		return restTemplateBuilder.build();
   	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
	  PropertySourcesPlaceholderConfigurer config = new PropertySourcesPlaceholderConfigurer();
	  config.setIgnoreUnresolvablePlaceholders(true);
	  return config;
	}
}
