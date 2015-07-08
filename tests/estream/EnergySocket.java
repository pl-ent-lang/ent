package estream;

public class EnergySocket@mode<X, Y <: X> {
  private BufferedEnergyOutputStream@mode<X,Y> stream;

  public EnergySocket(String host, int port) {
    stream = new BufferedEnergyOutputStream@mode<X,Y>();
  }

  public EnergyOutputStream@mode<X,Y> outputStream() {
    return this.stream;
  }

}
