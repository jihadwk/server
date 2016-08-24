package com.wk.config;
//fileName: /resources/config/properties/appConfig.properties
public class AppConfig extends BaseConfig {
	public int getAppId() {
		return Integer.parseInt(getConfig("current.appid"));
	}

	public byte getCreateRoleAble() {
		return Byte.parseByte(getConfig("server.status.createRoleAble"));
	}

	public int getMaxOnlineRole() {
		return Integer.parseInt(getConfig("server.status.maxOnlineRole"));
	}

	public int getMaxRoleLevel() {
		return Integer.parseInt(getConfig("server.status.maxRoleLevel"));
	}

	public String getVersion() {
		return getConfig("server.status.version");
	}

	public String getUpdateAddress() {
		return getConfig("server.status.updateAddress");
	}
	public String getBankServerAddress() {
		return getConfig("lootes.bank.server");
	}
}
