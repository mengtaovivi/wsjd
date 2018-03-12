/**   
* @date 2012-7-3 by YU
* @version V1.0   
*/
package nju.software.wsjx.exception;

/**
 * @author YU
 *˵��:ϵͳ������unchecked�쳣�Ļ���
 */
public class BaseAppException extends RuntimeException{

	/**
	* @Fields serialVersionUID
	*/
	private static final long serialVersionUID = 896610542617189773L;
	
	/**
     * BaseAppException�а������쳣��Ϣ
     */
    private String exceptionMessage;

    /**
     * �޲ι��췽��
     *  
     */
    public BaseAppException() {
    	
    }

    /**
     * �вι��췽��
     * <p>
     * 
     * @param msg
     *            �쳣��Ϣ
     *  
     */
    public BaseAppException(String msg) {
        this.exceptionMessage = msg;
    }

    /**
     * �вι��췽��
     * 
     * @param msg
     *            �쳣��Ϣ
     * @param e
     *            �쳣�����ĸ�Դ,Throwable object.
     *  
     */
    public BaseAppException(String msg, Throwable e) {
        this.exceptionMessage = msg;
        this.initCause(e);
    }

    /**
     * �����쳣�����ĸ�Դ
     * 
     * @param e
     *            �쳣�����ĸ�Դ,Throwable object.
     *  
     */
    public void setCause(Throwable e) {
        this.initCause(e);
    }

    /**
     * 
     * @see Object#toString()
     */
    public String toString() {
        String s = getClass().getName();
        return s + ": " + exceptionMessage;
    }

    /**
     * ����쳣��Ϣ. �ȼ���Exception.getMessage().
     * 
     * @see Throwable#getMessage()
     */
    public String getMessage() {
        return exceptionMessage;
    }

}
