
-- 开启事务  需要关闭事务自动提交 show variables like 'autocommit'; set autocommit = 0;
begin;
select * from  `product_stock` where product_id=1 for update;
update product_stock set stock=stock+1 where product_id=1;
--  提交事务
commit;

--  乐观锁一  2 3 2 会出现ABA的问题
update product_stock set stock=stock+1 where product_id=1 and  stock=#{stock};


--  乐观锁一  增加版本控制 每次修改产生记录
select * from  `product_stock` where product_id=1
update product_stock set stock=stock+1,version=version+1 where product_id=1 and  version=#{version};

select * from  `product_stock` where product_id=1
update product_stock set stock=stock-1,version=version+1 where product_id=1 and stock>0 and version=#{version};



-- https://www.jianshu.com/p/ed896335b3b4

select * from  `product_stock` where product_id=1

-- 以上update语句，在执行过程中，会在一次原子操作中自己查询一遍quantity的值，并将其扣减掉1。
-- 1.where查询符合条件的记录是当前读   2.update更新当前读的记录   3.多条就执行多次
update product_stock set stock=stock-1 where product_id=1 and  stock-1>0;




select * from  `product_stock` where product_id=1
update product_stock set stock=stock+1,version=version+1 where product_id=1 and  version=#{version};



-- 更新订单状态

select * from  `order` where id=1
update  `order` set order_status=?,version=version+1 where id=1 and  version=#{version};

