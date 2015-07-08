package estream;

public interface EnergyOutputStream@mode<X, X <: Y> {

  void close();
  void flush();

  void write(EnergyData@mode<Y> data);
  void write(List<Byte>@mode<Y> data);
  void write(byte@mode<Y> data);

}
