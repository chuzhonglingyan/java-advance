
-- 开启事务  需要关闭事务自动提交 show variables like 'autocommit'; set autocommit = 0;
begin;
select * from  `product_stock` where product_id=1 for update;
update product_stock set stock=stock+1 where product_id=1;
--  提交事务
commit;

--  乐观锁一  2 3 2 会出现ABA的问题
select * from  `product_stock` where product_id=1
update product_stock set stock=stock+1 where product_id=1 and  stock=#{stock};


--  乐观锁一  增加版本控制 每次修改产生记录
select * from  `product_stock` where product_id=1
update product_stock set stock=stock+1,version=version+1 where product_id=1 and  version=#{version};


--  乐观锁一  缩小颗粒度  以上update语句，在执行过程中，会在一次原子操作中自己查询一遍quantity的值，并将其扣减掉1。
select * from  `product_stock` where product_id=1
update product_stock set stock=stock-1 where product_id=1 and  stock-1>0;
