/**
 * 
 */
package com.morningsidevc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.morningsidevc.dao.gen.TagInfoMapper;
import com.morningsidevc.enums.TagStatus;
import com.morningsidevc.po.gen.TagInfo;
import com.morningsidevc.po.gen.TagInfoExample;
import com.morningsidevc.service.TagInfoService;
import com.morningsidevc.vo.Tag;

/**
 * @author yangna
 *
 */
@Component
public class TagInfoServiceImpl implements TagInfoService {
	
	@Resource
	private TagInfoMapper mapper;
	
	@Override
	public List<Tag> findTags() {
		List<Tag> tags = new ArrayList<Tag>();
		
		TagInfoExample example = new TagInfoExample();
		example.createCriteria().andStatusEqualTo(TagStatus.NORMAL.getValue());
		example.setOrderByClause("OrderNum ASC");
		List<TagInfo> tagInfos = mapper.selectByExample(example);
		
		if (!CollectionUtils.isEmpty(tagInfos)) {
			for (TagInfo tagInfo : tagInfos) {
				Tag tag = new Tag();
				tag.setTagName(tagInfo.getTagname());
				tags.add(tag);
			}
		} 

		
		return tags;
	}

}
