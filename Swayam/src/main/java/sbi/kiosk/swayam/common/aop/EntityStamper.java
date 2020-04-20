package sbi.kiosk.swayam.common.aop;



import java.lang.reflect.ParameterizedType;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.val;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.entity.Common;

@Aspect
@Component
public class EntityStamper {

	@Before("execution(public * sbi.kiosk.swayam.kioskmanagement.repository.* .*(..)) "
			+ "|| execution(public * sbi.kiosk.swayam.healthmonitoring.repository.* .*(..))")
    public void setTimestampsOnEntities(JoinPoint joinPoint) throws NoSuchMethodException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // only intercept save calls - that's when timestamp setting should happen
        if (!signature.getName().contains("save")) {
            return;
        }

        // need to go through target.class as Spring Data repositories will be proxied
        Class<?> domainRepositoryType = AopProxyUtils.proxiedUserInterfaces(joinPoint.getTarget())[0];

        // every Spring JPA repo has two generic parameters - entity type and key type
        Class<?> entityType = (Class<?>) ((ParameterizedType) domainRepositoryType.getGenericInterfaces()[0]).getActualTypeArguments()[0];

        if (!Common.class.isAssignableFrom(entityType)) {
            return;
        }

        val argBeingSaved = joinPoint.getArgs()[0];

        // save (single object or iteralbe of objects)
        if (argBeingSaved instanceof Iterable) {
            timestampAll((Iterable<Common>) argBeingSaved);
        } else {
            timestampOne((Common) argBeingSaved);
        }

    }

    private void timestampAll(Iterable<Common> entities) {
        entities.forEach(this::timestampOne);
    }
    
    public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

    private void timestampOne(Common withCreatedAt) {
    	UserDto user = (UserDto) session().getAttribute("userObj");

        // don't override creation date once it's been set
        if (StringUtils.isEmpty(withCreatedAt.getCreatedBy())) {
        	withCreatedAt.setCreatedBy(user.getPfId());
            withCreatedAt.setCreatedDate(new Date());
        }
        withCreatedAt.setModifiedDate(new Date());
        withCreatedAt.setModifiedBy(user.getPfId());
    }
}
