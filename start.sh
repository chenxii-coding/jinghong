#!/usr/bin sh

#所有参数
all_service=('jinghong-server' 'jinghong-config' 'jinghong-gateway' 'jinghong-goods' 'jinghong-user')
version=1.1.0
declare -a exec_params=(${@:1})

echo "本次启动参数为: ${exec_params[*]}"

if test ${#exec_params[*]} -eq 0; then
  echo '参数为空，默认启动所有应用'
  for i in "${all_service[@]}"; do
    nohup java -jar ./"${i}/target/${i}-${version}.jar" &
  done
else
  for i in "${exec_params[@]}"; do
    nohup java -jar ./"${i}/target/${i}-${version}.jar" &
  done
fi

if test $? -eq 0; then
  echo '应用启动成功'
else
  echo '有错误发生，请检查'
fi

echo '启动脚本执行完毕'
