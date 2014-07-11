package com.zzc.action;

import java.util.HashMap;
import java.util.Map;

import com.zzc.core.SpringAppContext;
import com.zzc.core.action.MyBaseAction;
import com.zzc.entity.VipMrg.BaseTrade;
import com.zzc.entity.VipMrg.ExtTrade;
import com.zzc.entity.VipMrg.TradeInfo;
import com.zzc.service.VipMrg.CommonSubmitService;
import com.zzc.service.VipMrg.TradeService;

public abstract class BasePageAction extends MyBaseAction {
	private BaseTrade baseTrade = new BaseTrade();
	private ExtTrade extTrade = new ExtTrade();
	public TradeInfo tradeInfo;
	
	public TradeService tradeService = this.getService("tradeService");
	
	/**
	 * 默认的调用方法
	 * @return
	 */
	public String execute(){
		try{
			//调用子类方法
			this.beforeSubmit();
			//调用服务
			String serviceName = this.tradeService.getTradeFinshSerivceName(tradeInfo.getTradeTypeCode());
			CommonSubmitService service = SpringAppContext.getBean(serviceName);
			service.submit(this.tradeInfo);
			
			Map<String,Object> msg = new HashMap<String, Object>();
			msg.put("success", true);
			this.putRetJsonVaule(msg);
		}catch (Exception e) {
			log.error("提交失败！",e);
			Map<String,Object> msg = new HashMap<String, Object>();
			msg.put("success", false);
			msg.put("msg", e.getMessage());
			this.putRetJsonVaule(msg);
		}
		//返回值
		return this.jsonResult;
	}
	/**
	 * 此方法所有子类进行重写
	 */
	public abstract void beforeSubmit();
	
	public TradeInfo getTradeInfo() {
		return tradeInfo;
	}

	public void setTradeInfo(TradeInfo tradeInfo) {
		this.tradeInfo = tradeInfo;
	}

	public BaseTrade getBaseTrade() {
		return baseTrade;
	}

	public void setBaseTrade(BaseTrade baseTrade) {
		this.baseTrade = baseTrade;
	}

	public ExtTrade getExtTrade() {
		return extTrade;
	}

	public void setExtTrade(ExtTrade extTrade) {
		this.extTrade = extTrade;
	}
}
