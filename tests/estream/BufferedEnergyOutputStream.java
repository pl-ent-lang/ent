package estream;

import java.util.ArrayList;
import java.util.List;

public class BufferedEnergyOutputStream@mode<X,Y> implements EnergyOutputStream@mode<X,Y> {
  private List<Byte> buf;

  public BufferedEnergyOutputStream() {
    this.buf = new ArrayList<Byte>();
  }

  void close() {
  }

  void flush() {
  }

  void write(EnergyData@mode<Y> data) {
    List<Byte> bytes = data.compress();
    this.write(bytes);
  }

  void write(List<Byte>@mode<Y> data) {
    this.buf.addAll(data);
  }

  void write(byte@mode<Y> data) {
    this.buf.add(data);
  }


}
