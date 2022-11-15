import java.util.stream.Stream;

import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

/**
 * Streaming class.
 *
 * <p>CS2030S Lab 9b</p>
 *
 * @author Adi Yoga S. Prabawa
 * @author David Zhu (Group 12B)
 * @version CS2030S AY 22/23 Sem 1
 */
public class Streaming {
  public static <T> List<Pair<Integer, T>> encode(Stream<T> stream) {
    return stream.collect(ArrayList::new, (acc, x) -> {
      int maxIdx = acc.size() - 1;
      if (maxIdx < 0) {
        acc.add(new Pair<>(1, x));
        return;
      }

      Pair<Integer, T> prevPair = acc.get(maxIdx);
      if (prevPair.getSnd().equals(x)) {
        prevPair.setFst(prevPair.getFst() + 1);
      } else {
        acc.add(new Pair<>(1, x));
      }
    }, (x, y) -> {
      int maxIdx = x.size() - 1;
      Pair<Integer, T> last = x.get(maxIdx);
      Pair<Integer, T> first = y.get(0);
      if (last.getSnd().equals(first.getSnd())) {
        first.setFst(last.getFst() + first.getFst());
        x.remove(maxIdx);
      }
      x.addAll(y);
    });
  }
}
