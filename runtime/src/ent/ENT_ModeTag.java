package ent.runtime;

public class ENT_ModeTag {
  public Object obj;
  public Integer offset;
  public boolean concrete;

  public ENT_ModeTag(Object obj, Integer offset, boolean concrete) {
    this.obj = obj;
    this.offset = offset;
    this.concrete = concrete;
  }
}
