/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description:
 * Version: 1.0
 * Date: 18-5-17 下午9:11
 * LastModified: 18-5-17 上午9:17
 */

/*
 *
 *  * ProjectName: springboot-parent
 *  * Author: tree.yu
 *  * Description: 仓库上下文对象
 *  * Version: 1.0
 *  * Date: 18-5-9 上午11:02
 *  * LastModified: 18-5-9 上午11:02
 *
 */

package com.vphoto.demo.springboot.manager.domain.repository;


import com.vphoto.demo.springboot.manager.client.IpClient;
import com.vphoto.demo.springboot.manager.redis.RedisHelper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("repositoryContext")
@Getter
public class RepositoryContext {

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private DemoModelRepository demoModelRepository;

//    @Autowired
//    private IpClient ipClient;

}
