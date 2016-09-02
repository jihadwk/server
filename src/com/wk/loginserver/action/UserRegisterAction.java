//package com.wk.loginserver.action;
//
//import org.apache.log4j.Logger;
//
//import com.bw.application.manager.user.IUserManager;
//import com.bw.application.message.UserRegisterInfo.UserRegisterRequest;
//import com.bw.application.message.UserRegisterInfo.UserRegisterResponse;
//import com.bw.baseJar.errorCode.ErrorCodeInterface;
//import com.bw.cache.vo.BwPlantUserVO;
//import com.commonSocket.net.action.Action;
//import com.commonSocket.net.action.Request;
//import com.commonSocket.net.action.Response;
//
//public class UserRegisterAction implements Action {
//	private Logger logger = Logger.getLogger(getClass());
//	private IUserManager userManager;
//	@Override
//	public String execute(Request paramRequest, Response paramResponse)
//			throws Exception {
//		UserRegisterRequest reqMsg = UserRegisterRequest.parseFrom(paramRequest
//				.getMessage());
//		UserRegisterResponse.Builder builder = UserRegisterResponse.newBuilder();
//		try {
//			BwPlantUserVO bwPlantUserVO=new BwPlantUserVO();
//			bwPlantUserVO.setMailaddress(reqMsg.getMailAddress());
//			bwPlantUserVO.setNickname(reqMsg.getNickName());
//			bwPlantUserVO.setPassword(reqMsg.getPassword());
//			bwPlantUserVO.setBoweiid(reqMsg.getTempMailAddress());
//			int result=userManager.UserRegister(bwPlantUserVO);
//			builder.setResult(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//			builder.setResult(ErrorCodeInterface.ERROR);
//			builder.setInfo(e.getMessage());
//			logger.error(e);
//			System.out.println("注册失败!");
//		}finally {
//			paramResponse.write(builder.build());
//		}
//		return null;
//	}
//	public IUserManager getUserManager() {
//		return userManager;
//	}
//	public void setUserManager(IUserManager userManager) {
//		this.userManager = userManager;
//	}
//
//}
