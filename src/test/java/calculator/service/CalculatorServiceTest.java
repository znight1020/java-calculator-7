package calculator.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorServiceTest {

    private CalculatorService service = new CalculatorService();

    @Test
    public void 커스텀_구분자_지정_형식_예외_테스트() {
        // given
        String input = "//;1;2;3";

        // when & then
        Assertions.assertThatThrownBy(() -> service.parseInputAndCalculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("커스텀 구분자의 지정 형식이 잘못되었습니다.");
    }

    @Test
    public void 커스텀_구분자_위치_예외_테스트() {
        // given
        String input = "1,2,3//;\n4,5";

        // when & then
        Assertions.assertThatThrownBy(() -> service.parseInputAndCalculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("커스텀 구분자는 입력 시작 위치에서만 지정할 수 있습니다.");
    }

    @Test
    public void 커스텀_구분자_여러번_지정_예외_테스트() {
        // given
        String input = "//;\\n1;2//s\\n3";

        // when & then
        Assertions.assertThatThrownBy(() -> service.parseInputAndCalculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("커스텀 구분자는 한 번만 지정할 수 있습니다.");
    }

    @Test
    public void 커스텀_구분자_문자열_예외_테스트() {
        // given
        String input = "//**\\n1**2**3";

        // when & then
        Assertions.assertThatThrownBy(() -> service.parseInputAndCalculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("커스텀 구분자는 공백, 문자열이 아닌 하나의 문자여야 합니다.");
    }

    @Test
    public void 커스텀_구분자_공백_예외_테스트() {
        // given
        String input = "//\\n1:2:3";

        // when & then
        Assertions.assertThatThrownBy(() -> service.parseInputAndCalculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("커스텀 구분자는 공백, 문자열이 아닌 하나의 문자여야 합니다.");
    }

    @Test
    public void 커스텀_구분자_숫자_예외_테스트() {
        // given
        String input = "//1\\n1,2,3";  // 숫자로 된 커스텀 구분자

        // when & then
        Assertions.assertThatThrownBy(() -> service.parseInputAndCalculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("커스텀 구분자는 숫자가 될 수 없습니다.");
    }
}
