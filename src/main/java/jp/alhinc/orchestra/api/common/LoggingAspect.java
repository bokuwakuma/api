package jp.alhinc.orchestra.api.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /*
    * Pointcut式メモ
    * ・メソッド名で指定
    * execution(* *..*Controller.*(..))
    * 指示子　戻り値　パッケージ　型クラス　メソッド　引数
    *
    * ・型で指定
    * within
    *
    * ・@が付くとアノテーション指定
    * */
    //@Before("within(jp.alhinc.orchestra.ticket.app.TicketController)")
//    @Before("execution(* *..*Controller.*(..))")
//    public void startLog(JoinPoint point) {
//        log.info("START " + point.getTarget().getClass() + "#" + point.getSignature().getName());
//    }
//
//    @AfterReturning("execution(* *..*Controller.*(..))")
//    public void endLog(JoinPoint point) {
//        log.info("END   " + point.getTarget().getClass() + "#" + point.getSignature().getName());
//    }


    // 効かない・・・？？
    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void controller() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {
    }

//    @Pointcut("execution(* jp.alhinc.orchestra..*.*(..))")
//    public void anyProjectMethodExecution() {
//    }

//    @Around("controller() && requestMapping() && anyProjectMethodExecution()")
    @Around("controller() && requestMapping()")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        Signature sig = pjp.getSignature();
        // FooController#index のようにControllerクラス名とメソッド名を取得
        log.info("START " + sig.getDeclaringType().getSimpleName() + "#" + sig.getName());
        Object retVal = pjp.proceed();
        log.info("END   " + sig.getDeclaringType().getSimpleName() + "#" + sig.getName());
        return retVal;
    }

    /**
     * 例外ハンドリング
     */
}
