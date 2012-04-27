package javax.sip.viewer.model;

import java.io.Serializable;

public class SipMessage implements Serializable, Comparable<SipMessage> {
  private String mMessageAsText = "";
  private String mSource = null;
  private String mDestination = null;
  private long mDelay;
  private long mTime;

  public void setSource(String pSource) {
    mSource = pSource;
  }

  public void setDestination(String pDestination) {
    mDestination = pDestination;
  }

  public void setDelay(long delay) {
    mDelay = delay;
  }

  public void setMessageAsText(String pText) {
    mMessageAsText = pText;
  }

  public String getSource() {
    return mSource;
  }

  public String getDestination() {
    return mDestination;
  }

  public long getDelay() {
    return mDelay;
  }

  public String getMessageAsText() {
    return mMessageAsText;
  }

  public String getFirstLine() {
    return mMessageAsText.split("\n")[0];
  }

  public void setTime(long pTime) {
    mTime = pTime;
  }

  public long getTime() {
    return mTime;
  }

  /**
   * 
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(SipMessage pSipMessage) {
    int lResult = 1;
    if (pSipMessage != null) {
      lResult = (int) (this.getDelay() - pSipMessage.getDelay());
      // hack to allow duplicate items
      if (lResult == 0) {
        lResult = 1;
      }
    }
    return lResult;
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  public boolean equals(Object obj2) {
    SipMessage otherMessage = (SipMessage) obj2;
    // a message is considered the same when the content is equals and the time between message is
    // less than 50 ms (first retransmit)
    return otherMessage.mMessageAsText.equals(mMessageAsText) && otherMessage.getTime() - mTime < 50;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "" + hashCode();
  }
}
