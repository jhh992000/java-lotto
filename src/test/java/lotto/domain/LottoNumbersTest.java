package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@DisplayName("로또번호 (일급콜렉션) 테스트")
public class LottoNumbersTest {

	@Test
	@DisplayName("생성된 로또번호가 6개인지 테스트")
	void createLottoNumbers_sizeCheck() {
		Assertions.assertThat(new LottoNumbers(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6})).hasSize(6)).isTrue();

		Assertions.assertThatThrownBy(() -> new LottoNumbers(Arrays.asList(new Integer[]{1, 2, 3, 4, 5})))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("중복된 로또번호가 존재할경우 예외발생 테스트")
	void createLottoNumbers_dupCheck() {
		Assertions.assertThatThrownBy(() -> new LottoNumbers(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 5})))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("랜덤생성기를 이용한 로또번호 생성 테스트")
	void createLottoNumbersByRandomNumberGenerator() {
		LottoRandomNumbersGenerator randomNumbersGenerator = new LottoRandomNumbersGenerator();
		LottoNumbers lottoNumbers = new LottoNumbers(randomNumbersGenerator);
		Assertions.assertThat(lottoNumbers.hasSize(6)).isTrue();
	}

}
