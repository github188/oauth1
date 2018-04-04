package com.yitutech.common.exception;

public class DicomParserException extends Exception {

  private static final long serialVersionUID = -8600869676860549753L;

  public static final int WW_WC_IS_NOT_VALID = -119;
  public static final int SLICE_HEIGHT_IS_NOT_VALID = -120;
  public static final int SLICE_IS_NOT_CONTINUITY = -121;
  public static final int SLICE_IS_NOT_COMPLETE = -122;
  public static final int IMAGE_TYPE_IS_NOT_VALID = -123;
  public static final int SLICE_THICKNESS_INVALID = -116;

  public static final int NOT_CHECK_SERIES = -200;

  private int errorCode;

  public DicomParserException(int errorCode) {
    super();
    this.errorCode = errorCode;
  }

  public DicomParserException() {
    super();
  }

  public int getErrorCode() {
    return errorCode;
  }

}
