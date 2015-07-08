package estream;

import java.util.List;

public class EnergyData {
  private List<Byte> bytes;

  attribute {
    return @mode<high>;
  }

  public EnergyData(List<Byte> bytes) {
    this.bytes = bytes;
  }

  public List<Byte> bytes() {
    return this.bytes;
  }

  public List<Byte> compress() {
    return this.bytes;
  }

}
