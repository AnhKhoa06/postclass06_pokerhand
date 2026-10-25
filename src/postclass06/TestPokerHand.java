package postclass06;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Disabled;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import e3.chapter5.Card;
import e3.chapter5.Rank;
import e3.chapter5.Suit;

public class TestPokerHand {

	// Hàm trợ giúp: dùng Reflection để gọi phương thức private isStraightFlush
	private static boolean invokeIsStraightFlush(PokerHand pHand) {
		try {
			Method method = PokerHand.class.getDeclaredMethod("isStraightFlush");
			method.setAccessible(true);
			return (boolean) method.invoke(pHand);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
			fail();
			return false;
		}
	}

	// Exercise 8: một trường hợp cho kết quả true (sảnh đồng chất 2-3-4-5-6 chất CLUBS)
	@Test
	void testIsStraightFlush_True() {
		PokerHand hand = new PokerHand(Card.get(Rank.TWO, Suit.CLUBS),
				Card.get(Rank.THREE, Suit.CLUBS),
				Card.get(Rank.FOUR, Suit.CLUBS),
				Card.get(Rank.FIVE, Suit.CLUBS),
				Card.get(Rank.SIX, Suit.CLUBS));
		assertTrue(invokeIsStraightFlush(hand));
	}

	// Exercise 9 và 10: lá cuối khác chất, các rank vẫn liên tiếp
	@Test
	void testIsStraightFlush_WrongSuit() {
		PokerHand hand = new PokerHand(Card.get(Rank.TWO, Suit.CLUBS),
				Card.get(Rank.THREE, Suit.CLUBS),
				Card.get(Rank.FOUR, Suit.CLUBS),
				Card.get(Rank.FIVE, Suit.CLUBS),
				Card.get(Rank.SIX, Suit.DIAMONDS));
		assertFalse(invokeIsStraightFlush(hand));
	}

	// Exercise 10: cùng chất nhưng rank bị đứt quãng (từ 5 nhảy lên 7)
	@Test
	void testIsStraightFlush_WrongRank() {
		PokerHand hand = new PokerHand(Card.get(Rank.TWO, Suit.CLUBS),
				Card.get(Rank.THREE, Suit.CLUBS),
				Card.get(Rank.FOUR, Suit.CLUBS),
				Card.get(Rank.FIVE, Suit.CLUBS),
				Card.get(Rank.SEVEN, Suit.CLUBS));
		assertFalse(invokeIsStraightFlush(hand));
	}
}