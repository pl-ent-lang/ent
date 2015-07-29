package estream;

import java.util.List;

modes { low <: mid; mid <: high; }

public interface EnergyOutputStream@mode<T, U<=T> {

  void close();
  void flush();

  void write(EnergyData@mode<U> data);
  void write(List<Byte>@mode<U> data);
  void write(byte@mode<U> data);

}
