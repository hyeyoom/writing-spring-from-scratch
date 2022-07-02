package com.github.hyeyoom.springframework.context;

import com.github.hyeyoom.springframework.beans.factory.BeanFactory;


/**
 * 애플리케이션에 대한 설정을 제공하는 중앙 인터페이스.
 * 애플리케이션이 실행 중에는 read-only이지만 구현체가 지원한다면 리로딩도 가능하다.
 *
 * ApplicationContext 제공 목록:
 * - 애플리케이션 컴포넌트에 접근하기 위한 Bean 팩토리 메소드. ListableBeanFactory로부터 파생됨
 * - 일반적인 방법으로 파일 리소스를 로드하는 기능. ResourceLoader 인터페이스로부터 파생됨
 * - 등록된 리스너에 이벤트를 발행하는 기능. ApplicationEventPublisher 인터페이스로부터 파생됨
 * - 국제화 지원, 메세지 resolve 기능. MessageSource 인터페이스로부터 파생됨
 *
 * 부모 컨텍스트로부터 파생된다. 후손 컨텍스트에서 정의된 것이 항상 우위를 가져간다.
 * 이 의미는 예를들어 각각의 서블릿이 다른 서블릿에 대해 독립적인 자신만의 자식 컨텍스트를 가지면서,
 * 전체 웹 애플리케이션이 단일 부모 컨텍스트를 사용할 수 있다.
 */
public interface ApplicationContext extends BeanFactory {
}
