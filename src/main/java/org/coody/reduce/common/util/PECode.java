package org.coody.reduce.common.util;

import java.util.Stack;

/**
 * Created By 谭健 2017年6月17日 22:17:04（TEL:15197447018）
 *
 * @version 2.0
 *
 *
 *          加解密算法
 */
public class PECode {

	/**
	 * 打乱改字符数组的组合顺序，可以得到不同的转换结果
	 */
	private static final char[] array = { 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g',
			'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm', '8', '5', '2', '7', '3', '6', '4', '0', '9', '1',
			'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X',
			'C', 'V', 'B', 'N', 'M' };

	public static String encode(Long number) {
		long rest = number;
		Stack<Character> stack = new Stack<Character>();
		StringBuilder result = new StringBuilder(0);
		while (rest >= 1) {
			stack.add(array[(int) (rest % array.length)]);
			rest = rest / array.length;
		}
		while (!stack.isEmpty()) {
			result.append(stack.pop());
		}
		return result.toString();

	}

	public static Long decode(String input) {
		long multiple = 1;
		Long result = 0L;
		Character temp;
		for (int i = 0; i < input.length(); i++) {
			temp = input.charAt(input.length() - i - 1);
			result += indexAt(temp) * multiple;
			multiple = multiple * array.length;
		}
		return result;
	}

	private static int indexAt(Character c) {
		for (int i = 0; i < array.length; i++) {
			if (c == array[i]) {
				return i;
			}
		}
		return -1;
	}

}
