package paper_ex2;

import java.util.*;

modes { energy_saver <: managed; managed <: full_throttle; }

class Main {
  public static void main(String[] args) {
    Agent da = new Agent@mode<?>(args[0]);
    ArraySet urls = new ArraySet(args[1]);
    while(true) {
      ArraySet newbie = new ArraySet();
      Agent a = snapshot da ?mode[];
      for (String s : urls) {
        newbie = a.work(s);
        urls.remove(s).add(newbie);
      }
    }
  }
}
