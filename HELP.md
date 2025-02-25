# Getting Started

### Reference Documentation
1. Maven dependencies:
    * spring web, lombook, jpa, postgresql, security, thymeleaf, docker-compose, spring-dev-tools
    * jakarta-validation, validation-api, mapstruct and mapstruct-processor
2. Compose file configuration
   database selection, dbname, username, password,...
3. Application properties configuration:
    * application.properties, application-dev.properties, application-prod.properties
4. Migration Scripts:
5. Models/Entities:
    * User, Role, Permission, UserRoles, RolePermissions
    * Post, Comment, Like, Share
    * Unique, Id, GeneratedStrategy, Column, length, defauls
6. Repositories:
    * UserRepository, RoleRepository, PermissionRepository, UserRolesRepository, RolePermissionsRepository
    * add also here custom queries methods findAllBy, findBy, countBy, countAllBy....
7. DTOs:
    * UserDto, RoleDto, PermissionDto, UserRolesDto, RolePermissionsDto
    * PostDto, CommentDto, LikeDto, ShareDto
    * Menyra se qysh na vijne te dhenat nga front/ thymeleaf ose javascript
    * Menyra se qysh ia dergojme frontit
    * Ne Hyrje te DTOs duhet me i validu te dhanat
8. Mappers:
    * UserMapper, RoleMapper, PermissionMapper, UserRolesMapper, RolePermissionsMapper
    * PostMapper, CommentMapper, LikeMapper
9. Service:
    * UserService, RoleService, PermissionService, UserRolesService, RolePermissionsService
    * PostService, CommentService, LikeService, ShareService
10. Controller and RestController
    * Controller: UserController, RoleController, PermissionController, UserRolesController, RolePermissionsController
    * RestController: PostRestController, CommentApiController, LikeController, ShareController
    * Controller: HTML + THymeleaf (Java shkruan html per ne
    * RestController: JSON/XML (Java shkruan json/xml per ne) kurse HTML shkruajm nepermjet javascript
