package com.huayi.wei.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayi.common.Constants;
import com.huayi.wei.model.WxArticle;
import com.huayi.wei.model.WxArticleNews;
import com.huayi.wei.model.WxNews;
import com.huayi.wei.service.WxArticleNewsService;
import com.huayi.wei.service.WxArticleService;
import com.huayi.wei.service.WxNewsService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage.Item;
import me.chanjar.weixin.mp.builder.outxml.NewsBuilder;
/**
 * 
* @ClassName: MenuHandler 
* @Description: 菜单事件处理
* @author panlei
* @date 2017年10月16日 下午2:03:06 
*
 */
@Service
public class MenuHandler implements WxMpMessageHandler{
	private Logger logger = LoggerFactory.getLogger(MenuHandler.class);
	
	@Autowired
	private WxArticleService wxArticleService;
	
	@Autowired
	private WxNewsService wxNewsService;
	
	@Autowired
	private WxArticleNewsService wxArticleNewsService;
	
	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		logger.info("菜单事件处理");
		String eventKey = wxMessage.getEventKey();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("enable", Constants.ENABLE_NO);
		params.put("keyValue", eventKey);
		List<WxArticle> wxArticles = wxArticleService.queryAllList(params);
		if(wxArticles !=null && wxArticles.size()>0) {
			WxArticle wxArticle = wxArticles.get(0);
			Map<String,Object> searchParams = new HashMap<String,Object>();
			searchParams.put("articleId", wxArticle.getId());
			List<WxArticleNews> wxArticleNews = wxArticleNewsService.queryAllList(searchParams);
			if(wxArticleNews != null && wxArticleNews.size()>0) {
				NewsBuilder builder = WxMpXmlOutMessage.NEWS();
				for(WxArticleNews news :wxArticleNews) {
					WxNews wxNews = wxNewsService.queryDBById(news.getId());
					Item item = new Item();
					item.setDescription(wxNews.getDescription());
					item.setPicUrl("http://coolservice.free.ngrok.cc/huayi/image/view/wx?path="+wxNews.getPicUrl());
					item.setTitle(wxNews.getTitle());
					item.setUrl(wxNews.getUrl());
					builder.addArticle(item);
				}
				WxMpXmlOutNewsMessage message = 	builder.fromUser(wxMessage.getToUser())
						.toUser(wxMessage.getFromUser()).build();
				return message;
			}
		}
		return null;
	}

}
