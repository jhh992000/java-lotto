package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoRandomNumbersGenerator implements RandomNumbersGenerator {

	private final List<Integer> numbers;

	public LottoRandomNumbersGenerator() {
		this.numbers = new ArrayList<>();
		for (int number = LottoNumber.MIN; number <= LottoNumber.MAX; number++) {
			numbers.add(number);
		}
	}

	@Override
	public List<Integer> generateNumbers() {
		List<Integer> numbers = this.generateRandomNumbers(Lotto.NUMBER_LENGTH);
		Collections.sort(numbers);
		return numbers;
	}

	private List<Integer> generateRandomNumbers(int length) {
		Collections.shuffle(this.numbers);
		return new ArrayList<>(this.numbers.subList(this.numbers.size() - length, this.numbers.size()));
	}

}
