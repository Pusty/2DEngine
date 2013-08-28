package me.engine.side;

import me.engine.packet.Packet;

public abstract class  Side {
  public abstract String getSide();
  public abstract void sendPacket(Packet p);
  
}
