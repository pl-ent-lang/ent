package estream;

import java.util.ArrayList;
import java.util.List;

public class BufferedEnergyOutputStream@mode<X,Y<=X> implements EnergyOutputStream@mode<X,Y> {
  private List<Byte> buf;

  public BufferedEnergyOutputStream() {
    this.buf = new ArrayList<Byte>();
  }

  public void close() {
  }

  public void flush() {
  }

  public void write(EnergyData@mode<Y> data) {
    List<Byte> bytes = data.compress();
    this.write(bytes);
  }

  public void write(List<Byte>@mode<Y> data) {
    this.buf.addAll(data);
  }

  public void write(byte@mode<Y> data) {
    this.buf.add(data);
  }


}
