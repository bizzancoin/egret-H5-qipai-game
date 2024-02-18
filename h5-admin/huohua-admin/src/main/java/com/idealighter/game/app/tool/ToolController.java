package com.idealighter.game.app.tool;
// package com.idealighter.game.app.tool;
//
// import com.idealighter.game.common.Result;
// import com.idealighter.game.service.usersystem.systemcfg.ISystemCfgService;
// import com.idealighter.utils.code.RandCodeUtil;
// import com.idealighter.utils.time.TimeUtil;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;
//
// import java.io.BufferedOutputStream;
// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.IOException;
//
// @RestController
// @RequestMapping("/customer")
// @Validated
// public class ToolController {
//
// @Value("${imgs.basepath}")
// private String directory;
// @Value("${imgs.prefix}")
// private String imgsprefix;
//
// @Autowired
// private ISystemCfgService systemCfgService;
//
// @PostMapping(value = "/uploadFile")
// public Result uploadFile(@RequestParam("file") MultipartFile uploadfile) throws IOException {
//
//
// // Get the filename and build the local file path
// String filename = uploadfile.getOriginalFilename();
// filename = TimeUtil.getTimeMillis() + RandCodeUtil.rand6Int() + getExtension(filename);
// String filepath = directory + File.separator + filename;
//
// // Save the file locally
// BufferedOutputStream stream =
// new BufferedOutputStream(new FileOutputStream(new File(filepath)));
// stream.write(uploadfile.getBytes());
// stream.close();
//
// String url = imgsprefix + filename;
// systemCfgService.saveCustomServiceImgs(url);
//
// Result result = new Result();
// result.getMap().put("url", url);
// return result;
// }
//
// @GetMapping("/getUrl")
// public Result getImgs() {
// String url = systemCfgService.getCustomServiceImgs();
// Result result = new Result();
// result.getMap().put("url", url);
// return result;
// }
//
// private String getExtension(String filename) {
// return filename.substring(filename.lastIndexOf("."));
// }
//
//
//
// }
