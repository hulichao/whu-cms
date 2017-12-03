package tech.whu.cms.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.whu.basic.model.BackupFile;



import tech.whu.basic.model.SystemContext;
import tech.whu.basic.util.BackupFileUtil;
import tech.whu.cms.auth.AuthClass;
import tech.whu.cms.service.IIndexService;
import tech.whu.cms.web.ResponseData;

@AuthClass
@Controller
@RequestMapping("/admin/backup")
public class BackupController {
	
	@Inject
	private IIndexService indexService;
	
	@RequestMapping({ "/backup", "/", "" })
	public String backup(Model model) {
		return "admin/backup";
	}
	
	@ResponseBody
	@RequestMapping("/listBackups")
	public List<BackupFile> list() {
		BackupFileUtil bfu = BackupFileUtil.getInstance(SystemContext.getRealPath());
		return bfu.listBackups();
	}

	@ResponseBody
    @RequestMapping("/delete")
	public ResponseData delete(String name) {
        BackupFileUtil bfu = BackupFileUtil.getInstance(SystemContext.getRealPath());
        bfu.delete(name);
		return ResponseData.SUCCESS_NO_DATA;
	}

    @ResponseBody
    @RequestMapping("/resume")
	public ResponseData resume(String name) {
        BackupFileUtil bfu = BackupFileUtil.getInstance(SystemContext.getRealPath());
        bfu.resume(name);
        indexService.generateTop();
        indexService.generateBody();
        indexService.generateBottom();
        return ResponseData.SUCCESS_NO_DATA;
    }

    @ResponseBody
    @RequestMapping("/add")
    public ResponseData add(String name) {
        BackupFileUtil bfu = BackupFileUtil.getInstance(SystemContext.getRealPath());
        bfu.backup(name);
        return ResponseData.SUCCESS_NO_DATA;
    }

}
