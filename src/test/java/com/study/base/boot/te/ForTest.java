package com.study.base.boot.te;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ForTest {

	@Nested
	public class FOR문{

		@Test
		void for문_테스트1() {
			List<String> forlist = new ArrayList<>(Arrays.asList("11","22","33","44","55","66"));

			for (String i : forlist) {
				forlist.remove(i);
				System.out.println("i ::: " + i);
			}
		}
		@Test
		void for문_테스트2() {
			List<String> forlist = new ArrayList<>(Arrays.asList("11","22","33","44","55","66"));

			List<String> forlist2 = new ArrayList<>();
			forlist2.addAll(forlist);

			StringBuilder builder = new StringBuilder();

			for (String i : forlist) {
				forlist2.remove(i);
				builder.append(i+"\n");
				if(!forlist2.isEmpty())
				builder.append(" UNION ");
			}
			System.out.println("build :: " + builder);
		}
	}
}
