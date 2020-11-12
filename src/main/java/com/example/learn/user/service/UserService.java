package com.example.learn.user.service;

import javax.annotation.Resource;

import com.example.learn.user.entity.UserEntity;
import com.example.learn.user.repository.UserRepository;
import com.example.learn.utils.ResultUtil;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Resource
	UserRepository userRepository;

	/**
	 * 查询所有数据
	 * @param pageIndex
	 * @param pageSize
	 * @param userEntity
	 * @return
	 */
	public ResultUtil findAll(String pageIndex, String pageSize, UserEntity userEntity) {
		try {
			int index = Integer.parseInt(pageIndex);
			int size = Integer.parseInt(pageSize);
			PageRequest pageRequest = PageRequest.of(index, size);
			Slice<UserEntity> records = userRepository.findAll(pageRequest);

			return ResultUtil.success().render(records);
		} catch (Exception e) {
			return ResultUtil.error(e.getMessage());
		}
	}

	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 */
	public ResultUtil findById(Long id) {
		try {
			return ResultUtil.success().render(userRepository.findById(id));
		} catch (Exception e) {
			return ResultUtil.error(e.getMessage());
		}
	}

	/**
	 * 删除一条数据
	 * @param id
	 * @return
	 */
	public ResultUtil delete(Long id) {
		try {
			return ResultUtil.success().render(userRepository.deleteById(id));
		} catch (Exception e) {
			return ResultUtil.error(e.getMessage());
		}
	}

	/**
	 * 新增一条数据
	 * @param userEntity
	 * @param userEntity.id 更新一条数据
	 * @return
	 */
	public ResultUtil save(UserEntity userEntity) {
		try {
			userRepository.save(userEntity);
			return ResultUtil.success().render(userEntity);
		} catch (Exception e) {
			return ResultUtil.error(e.getMessage());
		}
	}
}
