package com.project.GovNetMISApplication.config;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import com.project.GovNetMISApplication.ExceptionHandlingFiles.CustomAccessDeniedHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
        private final jwtAuthenticationFilter jwtAuthFilter;
        @Autowired
        private CustomAccessDeniedHandler accessDeniedHandler;

        @Bean
        public CorsFilter corsFilter() {
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowCredentials(true);
                config.addAllowedOrigin("http://localhost:5173");
                config.addAllowedOrigin("http://localhost:8081"); // Add your React app URL
                config.addAllowedOrigin("http://103.132.98.197:5173");
                config.addAllowedOrigin("http://103.132.98.197:8081");
                config.addAllowedHeader("*");
                config.addAllowedMethod("*");
                source.registerCorsConfiguration("/**", config);
                return new CorsFilter(source);

        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

                return httpSecurity
                                // .cors().configurationSource(corsConfigurationSource).and()
                                // .cors(cors -> cors.configurationSource(corsConfigurationSource))

                                .csrf(csrf -> csrf.disable())
                                .exceptionHandling(exception -> exception.authenticationEntryPoint(accessDeniedHandler)
                                                .accessDeniedHandler(accessDeniedHandler))
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(authorize -> authorize

                                                .requestMatchers("/uploads/**").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/auth/signin").permitAll()
                                                // .requestMatchers("/department/**").hasRole("department_creation")
                                                .requestMatchers("/committee/getSentDocumentsByDepartmentByType")
                                                .permitAll()
                                                .requestMatchers("/user/all").hasRole("user_all")
                                                .requestMatchers("/user/get").hasRole("getSingleUser")
                                                .requestMatchers("/user/change-password").hasRole("change_password")
                                                .requestMatchers("/user/add").hasRole("user_creation")
                                                .requestMatchers("/user/enableuser/**").hasRole("change_user_status")
                                                .requestMatchers("/user/disableuser/**").hasRole("change_user_status")
                                                .requestMatchers("/department/add").hasRole("department_creation")
                                                .requestMatchers("/department/update").hasRole("department_updation")
                                                .requestMatchers("/department/all").hasRole("all_departments_view")
                                                .requestMatchers("/reference/add").hasRole("add_document_reference")
                                                .requestMatchers("/reference/update").hasRole("edit_document_reference")
                                                .requestMatchers("/reference/getById")
                                                .hasAnyRole("view_all_document_references", "delete_document_reference")
                                                .requestMatchers("/reference/all")
                                                .hasRole("view_all_document_references")
                                                .requestMatchers("/user/update").hasAnyRole("user_edit", "edit_profile")
                                                .requestMatchers("/ActivityLog/all").hasRole("log_view")
                                                .requestMatchers("/ActivityLog/getDepartmentWise")
                                                .hasRole("log_view_DepartmentWise")
                                                .requestMatchers("/comment/add")
                                                .hasAnyRole("comment_add", "reply_comment")
                                                .requestMatchers("/comment/getAllCommentsBySendDocId")
                                                .hasRole("comments_view")
                                                .requestMatchers("/DocumentReport/add").hasRole("report_add")
                                                .requestMatchers("/document/add").hasRole("document_creation")
                                                .requestMatchers("/document/delete").hasRole("document_delete")
                                                .requestMatchers("/document/updateDocument").hasRole("document_update")
                                                .requestMatchers("/document/getDocumentById").hasRole("document_view")
                                                .requestMatchers("/document/getByDepartment").hasRole("document_list")
                                                .requestMatchers("/role/add").hasRole("role_creation")
                                                .requestMatchers("/role/all").hasRole("role_list")
                                                .requestMatchers("/per/all").hasRole("role_list")
                                                .requestMatchers("/role//assign-permissions").hasRole("role_update")
                                                .requestMatchers("/sendDocument/add").hasRole("document_share")
                                                .requestMatchers("/sendDocument/filter").hasRole("document_inbox")
                                                .requestMatchers("/sendDocument/filter1").hasRole("document_outbox")
                                                .requestMatchers("/sendDocument/ExpiredDocuments")
                                                .hasRole("view_expired_document_list")
                                                .requestMatchers("/auth/login").permitAll()
                                                .requestMatchers("/linking/add").hasRole("add_linked_documents")
                                                .requestMatchers("/linking/delete/**")
                                                .hasRole("delete_linked_documents")
                                                .requestMatchers("/document/forlinking").hasRole("add_linked_documents")
                                                .requestMatchers("/document/linkings/**")
                                                .hasRole("view_linked_documents")
                                                .requestMatchers("/ActivityLog/all").hasRole("log_view")
                                                .requestMatchers("/document/removeAppendantDoc/**")
                                                .hasRole("delete_append")
                                                .requestMatchers("/document/addAppendantDoc/**").hasRole("add_append")
                                                .requestMatchers("/sendDocument/getSendDocTree/**")
                                                .hasRole("view_document_tracking")
                                                .requestMatchers("/department/update/**").hasRole("department_update")
                                                .requestMatchers("/department/add").hasRole("department_creation")
                                                .requestMatchers("/user/add").hasRole("user_creation")
                                                .anyRequest().permitAll())
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                                .addFilterBefore(corsFilter(), jwtAuthenticationFilter.class)
                                // .logout(logout -> logout.logoutUrl("/logout")
                                // .addLogoutHandler(logoutHandler)
                                // .logoutSuccessHandler((request, response,
                                // authentication) -> SecurityContextHolder
                                // .clearContext()))
                                .build();

        }
}
