package com.github.hyeyoom.springframework.beans.factory;


/**
 * 스프링 빈(bean) 컨테이너에 접근하기 위한 루트 컨테이너
 *
 * 빈(bean) 컨테이너의 기본 클라이언트 뷰.
 * ListableBeanFactory나 ConfigurableBeanFactory 같은 특수한 목적의 BeanFactory도 존재함
 *
 * 문자열 이름으로 각각 유니크하게 특정할 수 있는 여러 개의 빈 정의(BeanDefinition)를 가지는 오브젝트에 의해 구현되는 인터페이스
 * 팩토리는 빈 정의에 따라 포함된 객체의 독립적인 인스턴스를 리턴할 수도 있다. (프로토타입 디자인 패턴)
 * 혹은 단일 공용 인스턴스 (싱글톤 디자인 패턴에 비해 우위에 있는 대안으로, 팩토리의 스코프 내에서 싱글톤임)
 * 빈 팩토리 설정에 따라 반환되는 인스턴스의 타입이 결정되며 API는 동일하다.
 * 스프링 2부터, 애플리케이션 컨텍스트 구현체에 따라 스코프 확장도 가능하다. (웹에서 request, session)
 *
 * 이 접근법에서 포인트는, 빈 팩토리는 애플리케이션 컴포넌트의 중앙 레지스트리이며 애플리케이션 컴포넌트의 설정을 중앙화한다.
 * (예를들면 각 오브젝트가 더 이상 프로퍼티 파일을 읽을 필요가 없어진다.)
 *
 * 참고로 일반적으로 BeanFactory lookup 같이 설정을 pull하는 형태보다,
 * 세터나 생성자를 통해 애플리케이션 객체를 설정하는 DI(의존성 주입)에 의존하는 것이 낫다.
 * 스프링에서의 DI 기능은 이 BeanFactory 인터페이스와 그 하위 인터페이스를 사용해 구현한다.
 *
 * 일반적으로 BeanFactory는 설정 소스(XML 문서나 애노테이션같은) 내에 저장된 빈 정의들을 읽는다.
 * 그리고 beans 패키지를 사용해 빈을 설정한다.
 * 그러나 필요한 경우 자바코드를 직접 생성해 단순하게 바로 리턴할 수도 있다.
 * 이러한 정의들이 어떻게 저장되는지에 대한 제약사항은 없다: LDAP, RDBMS, XML, 프로퍼티 파일 등 모두 가능.
 * 구현체들에서는 빈들 사이의 레퍼런스 지원을 권장한다. (DI)
 *
 * 빈 팩토리 구현체는 표준 빈 라이프사이클 인터페이스를 지원하는 것도 가능하다.
 * 초기화 메소드의 전체 셋과 표준의 순서는 다음과 같다.
 *
 * BeanNameAware's {@code setBeanName}
 * BeanClassLoaderAware's {@code setBeanClassLoader}
 * BeanFactoryAware's {@code setBeanFactory}
 * EnvironmentAware's {@code setEnvironment}
 * EmbeddedValueResolverAware's {@code setEmbeddedValueResolver}
 * ResourceLoaderAware's {@code setResourceLoader}
 * (only applicable when running in an application context)
 * ApplicationEventPublisherAware's {@code setApplicationEventPublisher}
 * (only applicable when running in an application context)
 * MessageSourceAware's {@code setMessageSource}
 * (only applicable when running in an application context)
 * ApplicationContextAware's {@code setApplicationContext}
 * (only applicable when running in an application context)
 * ServletContextAware's {@code setServletContext}
 * (only applicable when running in a web application context)
 * {@code postProcessBeforeInitialization} methods of BeanPostProcessors
 * InitializingBean's {@code afterPropertiesSet}
 * a custom {@code init-method} definition
 * {@code postProcessAfterInitialization} methods of BeanPostProcessors
 *
 * 빈 팩토리가 셧다운 되는 경우, 다음 라이프사이클 메서드를 적용해라
 * {@code postProcessBeforeDestruction} methods of DestructionAwareBeanPostProcessors
 * DisposableBean's {@code destroy}
 * a custom {@code destroy-method} definition
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;

    boolean containsBean(String name);
}
