
-- 开启事务  需要关闭事务自动提交 show variables like 'autocommit'; set autocommit = 0;
begin;
select stock,id,product_id from  `product_stock` where product_id=1 for update;
update product_stock set stock=stock+1 where product_id=1;
--  提交事务
commit;


