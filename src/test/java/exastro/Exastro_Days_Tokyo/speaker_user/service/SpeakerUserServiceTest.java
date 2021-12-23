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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import exastro.Exastro_Days_Tokyo.speaker_user.repository.SpeakerRepository;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
public class SpeakerUserServiceTest {
	
	@Mock   // モックオブジェクトとして使用することを宣言
	private SpeakerRepository repository;
	
	@InjectMocks    // モックオブジェクトの注入
	private SpeakerUserService speakerUserService;
	
	@Test
	public void test_getSpeakerList() {
		
		// Mock設定
		List<Integer> speakerIdList = new ArrayList<>();
		when(repository.getSpeakerList(speakerIdList)).thenReturn(getSpeakerListMock0());
		
		// 対象メソッド実行
		List<String> speakerList = speakerUserService.getSpeakerList(speakerIdList);
		
		// 以下、結果確認
		assertThat(speakerList.isEmpty());
		
		// Mock設定２
		speakerIdList.add(1);
		speakerIdList.add(2);
		speakerIdList.add(3);
		when(repository.getSpeakerList(speakerIdList)).thenReturn(getSpeakerListMock3());
		
		// 対象メソッド実行
		speakerList = speakerUserService.getSpeakerList(speakerIdList);
		
		// 以下、結果確認
		assertThat(speakerList).hasSize(3);
		
		assertThat(speakerList.get(0), is("item1"));
		assertThat(speakerList.get(1), is("item2"));
		assertThat(speakerList.get(2), is("item3"));
		
	}
	
	// Test Data
	private List<String> getSpeakerListMock0() {
		
		List<String> testData = new ArrayList<>();
		
		return testData;
	}
	
	private List<String> getSpeakerListMock3() {
		
		List<String> testData = new ArrayList<>();
		
		testData.add("item1");
		testData.add("item2");
		testData.add("item3");
		
		return testData;
	}
}
