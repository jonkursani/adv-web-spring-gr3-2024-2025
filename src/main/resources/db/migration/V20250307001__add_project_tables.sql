-- departments table
create table departments (
    id serial primary key not null,
    name varchar(100) not null,
    location varchar(100)
);

-- employees table
create table employees (
    id serial primary key not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    department_id int,
    hire_date date,
    email varchar(100) unique,
    constraint fk_department foreign key (department_id) references departments(id)
);

-- missions table
create table missions (
    id serial primary key not null,
    name varchar(100) not null,
    start_date date,
    end_date date,
    description text
);

-- employees_missions table
create table employee_missions (
    id serial primary key not null,
    employee_id int,
    mission_id int,
    role varchar(50),
    constraint fk_employee foreign key (employee_id) references employees(id),
    constraint fk_mission foreign key (mission_id) references missions(id)
);