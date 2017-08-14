package tech.whu.cms.controller;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.whu.basic.util.EnumUtils;
import tech.whu.cms.auth.AuthClass;
import tech.whu.cms.model.CmsException;
import tech.whu.cms.model.Role;
import tech.whu.cms.model.RoleType;
import tech.whu.cms.service.IRoleService;
import tech.whu.cms.service.IUserService;
import tech.whu.cms.web.DataTableMap;
import tech.whu.cms.web.ResponseData;

@Controller
@RequestMapping("/admin/role")
@AuthClass
public class RoleController {
	@Inject
	private IRoleService roleService;
	@Inject
	private IUserService userService;

	@RequestMapping({ "/role", "/", "" })
	public String role(Model model) {
		model.addAttribute("types", EnumUtils.enum2Name(RoleType.class));
		return "admin/role";
	}

	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(Model model) {
		return DataTableMap.getMapData(roleService.findRole());
	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseData add(@Validated Role role, BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseData("操作失败" + br.getFieldError().toString());
		}
		roleService.add(role);
		return ResponseData.SUCCESS_NO_DATA;
	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseData edit(Integer id, @Validated Role role, BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseData("操作失败" + br.getFieldError().toString());
		}
		Role er = roleService.load(id);
		er.setName(role.getName());
		er.setRoleType(role.getRoleType());
		roleService.update(er);
		return ResponseData.SUCCESS_NO_DATA;
	}

	@ResponseBody
	@RequestMapping(value = "/delete")
	public ResponseData delete(Long[] ids) {
		try {
			for (Long id : ids) {
				roleService.delete(id.intValue());
			}
		} catch (CmsException e) {
			return new ResponseData(false, e.getMessage());
		}
		return ResponseData.SUCCESS_NO_DATA;
	}
	
	@ResponseBody
	@RequestMapping("/clearUsers")
	public ResponseData clearUsers(int id) {
		roleService.deleteRoleUsers(id);
		return ResponseData.SUCCESS_NO_DATA;
	}

}
