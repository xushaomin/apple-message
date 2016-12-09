package com.appleframework.message.provider.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.appleframework.message.entity.ThirdAuth;
import com.appleframework.message.provider.plus.MessagePlus;
import com.appleframework.message.provider.service.MessagePlusService;
import com.appleframework.message.service.PushTemplateService;
import com.appleframework.message.service.ThirdAuthService;

/**
 * @author xusm
 * 
 */
@Service("messagePlusService")
public class MessagePlusServiceImpl implements MessagePlusService {

	private static final Log logger = LogFactory.getLog(MessagePlusServiceImpl.class);

	@Resource
	private ThirdAuthService thirdAuthService;

	@Resource
	private PushTemplateService pushTemplateService;

	public MessagePlus genrate(Long id) {
		MessagePlus plus = null;
		try {
			ThirdAuth thirdAuth = thirdAuthService.get(id);
			if (null != thirdAuth) {
				Class<?> clazz = Class.forName(thirdAuth.getThirdClass());
				plus = (MessagePlus) clazz.newInstance();
				plus.setThirdKey(thirdAuth.getThirdKey());
				plus.setThirdSecret(thirdAuth.getThirdSecret());
				plus.setThirdExtend(thirdAuth.getThirdExtend());
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return plus;
	}
	
	@Override
	public List<MessagePlus> genrate(String ids) {
		List<MessagePlus> list = new ArrayList<MessagePlus>();
		try {
			String[] idss = ids.split(",");
			for (int i = 0; i < idss.length; i++) {
				String id = idss[i];
				if (null != id) {
					ThirdAuth thirdAuth = thirdAuthService.get(Long.parseLong(id));
					Class<?> clazz = Class.forName(thirdAuth.getThirdClass());
					MessagePlus plus = (MessagePlus) clazz.newInstance();
					plus.setThirdKey(thirdAuth.getThirdKey());
					plus.setThirdSecret(thirdAuth.getThirdSecret());
					plus.setThirdExtend(thirdAuth.getThirdExtend());
					list.add(plus);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return list;

	}

}
