package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("로또번호 (일급콜렉션) 테스트")
public class LottoNumbersTest {

	private LottoNumbers winLottoNumbers;

	@BeforeEach
	void setUp() {
		winLottoNumbers = new LottoNumbers(1, 2, 3, 4, 5, 6);
	}

	@Test
	@DisplayName("생성된 로또번호가 6개가 아닐 경우 예외발생 테스트")
	void createLottoNumbers_sizeCheck() {
		Assertions.assertThatThrownBy(() -> new LottoNumbers(1, 2, 3, 4, 5))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("중복된 로또번호가 존재할경우 예외발생 테스트")
	void createLottoNumbers_dupCheck() {
		Assertions.assertThatThrownBy(() -> new LottoNumbers(1, 2, 3, 4, 5, 5))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("랜덤생성기를 이용한 로또번호 생성 테스트")
	void createLottoNumbersByRandomNumberGenerator() {
		TestRandomNumbersGenerator randomNumbersGenerator = new TestRandomNumbersGenerator(new Integer[]{1, 2, 3, 4, 5, 6});
		LottoNumbers lottoNumbers = new LottoNumbers(randomNumbersGenerator);

		Assertions.assertThat(lottoNumbers.contains(new LottoNumber(1))).isTrue();
		Assertions.assertThat(lottoNumbers.contains(new LottoNumber(2))).isTrue();
		Assertions.assertThat(lottoNumbers.contains(new LottoNumber(3))).isTrue();
		Assertions.assertThat(lottoNumbers.contains(new LottoNumber(4))).isTrue();
		Assertions.assertThat(lottoNumbers.contains(new LottoNumber(5))).isTrue();
		Assertions.assertThat(lottoNumbers.contains(new LottoNumber(6))).isTrue();
		Assertions.assertThat(lottoNumbers.contains(new LottoNumber(7))).isFalse();
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6})
	@DisplayName("로또번호 리스트에 당첨번호가 포함되어 있는지 테스트")
	void contains(int number) {
		Assertions.assertThat(winLottoNumbers.contains(new LottoNumber(number))).isTrue();
	}

	@Test
	@DisplayName("당첨번호와 비교하여 랭킹을 구하는 테스트")
	void rank() {
		Assertions.assertThat(new LottoNumbers(1, 2, 3, 4, 5, 6)
				.findRank(winLottoNumbers)).isEqualTo(Rank.FIRST);

		Assertions.assertThat(new LottoNumbers(1, 2, 3, 4, 5, 45)
				.findRank(winLottoNumbers)).isEqualTo(Rank.SECOND);

		Assertions.assertThat(new LottoNumbers(1, 2, 3, 4, 44, 45)
				.findRank(winLottoNumbers)).isEqualTo(Rank.THIRD);

		Assertions.assertThat(new LottoNumbers(1, 2, 3, 43, 44, 45)
				.findRank(winLottoNumbers)).isEqualTo(Rank.FOURTH);
	}

}
