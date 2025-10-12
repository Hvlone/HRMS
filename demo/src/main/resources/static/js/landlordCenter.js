alert('JS 文件加载成功');
let selectedFileList = [];
document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('houseForm');
    const picsInput = document.getElementById('picsInput');
    const selectedFiles = document.getElementById('selectedFiles');
    const submitBtn = document.getElementById('submitBtn');

    // 显示选中的文件
    picsInput.addEventListener('change', function() {
        const newFiles = Array.from(this.files);
        selectedFileList = selectedFileList.concat(newFiles);
        if (selectedFileList.length > 0) {
            selectedFiles.textContent = `已选择 ${selectedFileList.length} 张图片`;
            selectedFiles.classList.remove('hidden');

            // 验证图片数量
            if (selectedFileList.length < 3) {
                selectedFiles.classList.add('text-red-500');
                selectedFiles.textContent += ' (至少需要3张图片)';
            } else {
                selectedFiles.classList.remove('text-red-500');
            }
        } else {
            selectedFiles.textContent = '未选择图片';
            selectedFiles.classList.remove('hidden');
            selectedFiles.classList.add('text-gray-500');
        }
    });

// 在重置按钮点击时也重置文件选择
    document.querySelector('button[type="reset"]').addEventListener('click', function() {
        setTimeout(resetFileInput, 100); // 稍后执行以确保表单重置完成
    });

    // 表单提交验证
    form.addEventListener('submit', function(e) {
        e.preventDefault(); // 阻止默认提交

        // 检查所有必填字段
        const requiredFields = form.querySelectorAll('[required]');
        let isValid = true;
        let errorMessage = '';

        requiredFields.forEach(field => {
            if (!field.value.trim()) {
                isValid = false;
                field.classList.add('border-red-500');
                errorMessage = '请填写所有必填字段！';
            } else {
                field.classList.remove('border-red-500');
            }
        });

        // 特殊检查：图片至少3张
        if (selectedFileList.length < 3) {
            isValid = false;
            errorMessage = '请至少上传3张图片！';
            selectedFiles.classList.add('text-red-500');
        } else {
            selectedFiles.classList.remove('text-red-500');
        }

        // 特殊检查：配套设施至少选择一项
        const facilityCheckboxes = form.querySelectorAll('input[name="houseFacility"]:checked');
        if (facilityCheckboxes.length === 0) {
            isValid = false;
            errorMessage = '请至少选择一项配套设施！';
        }

        if (!isValid) {
            // 显示错误提示
            showError(errorMessage);
            return;
        }

        // 所有验证通过，准备提交表单
        submitForm();
    });

    // 显示错误提示
    function showError(message) {
        // 移除现有的错误提示
        const existingError = document.querySelector('.form-error');
        if (existingError) {
            existingError.remove();
        }

        // 创建新的错误提示
        const errorDiv = document.createElement('div');
        errorDiv.className = 'form-error bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4';
        errorDiv.innerHTML = `
            <strong class="font-bold">错误！</strong>
            <span class="block sm:inline">${message}</span>
        `;

        // 在表单顶部插入错误提示
        form.insertBefore(errorDiv, form.firstChild);

        // 3秒后自动消失
        setTimeout(() => {
            if (errorDiv.parentNode) {
                errorDiv.remove();
            }
        }, 3000);
    }

    // 提交表单
    function submitForm() {
        const formData = new FormData(form);
        // 手动添加所有图片
        selectedFileList.forEach(file => {
            formData.append('pics', file);
        });
        // 禁用提交按钮，防止重复提交
        submitBtn.disabled = true;
        submitBtn.innerHTML = '<i class="fa fa-spinner fa-spin mr-2"></i> 提交中...';

        // 发送AJAX请求
        fetch('/house/addHouse', {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    // 成功提示
                    showSuccess(data.message || '房源发布成功！');
                    // 3秒后跳转或重置表单
                    setTimeout(() => {
                        form.reset();
                        selectedFiles.classList.add('hidden');
                        window.location.reload(); // 或者跳转到其他页面
                    }, 2000);
                } else {
                    showError(data.message || '发布失败，请重试！');
                }
            })
            .catch(error => {
                showError('网络错误，请检查后重试！');
                console.error('Error:', error);
            })
            .finally(() => {
                // 重新启用提交按钮
                submitBtn.disabled = false;
                submitBtn.innerHTML = '<i class="fa fa-paper-plane mr-2"></i> 发布房源';
            });
    }

    // 显示成功提示
    function showSuccess(message) {
        const successDiv = document.createElement('div');
        successDiv.className = 'form-success bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative mb-4';
        successDiv.innerHTML = `
            <strong class="font-bold">成功！</strong>
            <span class="block sm:inline">${message}</span>
        `;

        form.insertBefore(successDiv, form.firstChild);

        setTimeout(() => {
            if (successDiv.parentNode) {
                successDiv.remove();
            }
        }, 3000);
    }

    // 实时验证：输入时移除错误样式
    form.querySelectorAll('input, select, textarea').forEach(input => {
        input.addEventListener('input', function() {
            this.classList.remove('border-red-500');
        });
    });
});

// 退出登录
function logout() {
    localStorage.removeItem('currentLandlord');
    window.location.href = 'index.html';
}

// -------------------------- 模拟接口数据 --------------------------
// 1. 模拟预约看房记录数据
function getAppointmentsData() {
    return new Promise((resolve) => {
        // 模拟接口延迟 800ms
        setTimeout(() => {
            resolve([
                {
                    id: 1,
                    houseImg: "https://picsum.photos/id/1029/80/80",
                    houseTitle: "幸福家园 一室一厅",
                    housePrice: 2800,
                    visitorName: "李先生",
                    visitorPhone: "138****6789",
                    appointTime: "2023-11-12 10:00",
                    status: "已预约",
                    statusColor: "blue",
                    operations: [
                        { type: "confirm", text: "确认", icon: "fa-check", color: "green" },
                        { type: "cancel", text: "取消", icon: "fa-times", color: "red" }
                    ]
                },
                {
                    id: 2,
                    houseImg: "https://picsum.photos/id/239/80/80",
                    houseTitle: "丽景花园 两室两厅",
                    housePrice: 3600,
                    visitorName: "张女士",
                    visitorPhone: "139****5678",
                    appointTime: "2023-11-11 15:30",
                    status: "已预约",
                    statusColor: "blue",
                    operations: [
                        { type: "confirm", text: "确认", icon: "fa-check", color: "green" },
                        { type: "cancel", text: "取消", icon: "fa-times", color: "red" }
                    ]
                },
                {
                    id: 3,
                    houseImg: "https://picsum.photos/id/1029/80/80",
                    houseTitle: "幸福家园 一室一厅",
                    housePrice: 2800,
                    visitorName: "王先生",
                    visitorPhone: "136****2345",
                    appointTime: "2023-11-10 09:30",
                    status: "已确认",
                    statusColor: "green",
                    operations: [
                        { type: "cancel", text: "取消", icon: "fa-times", color: "red" }
                    ]
                },
                {
                    id: 4,
                    houseImg: "https://picsum.photos/id/164/80/80",
                    houseTitle: "城市之光 三室两厅",
                    housePrice: 4500,
                    visitorName: "赵女士",
                    visitorPhone: "135****7890",
                    appointTime: "2023-11-08 14:00",
                    status: "已取消",
                    statusColor: "red",
                    operations: [
                        { type: "view", text: "详情", icon: "fa-eye", color: "gray" }
                    ]
                }
            ]);
        }, 800);
    });
}

// 2. 模拟看房记录数据
function getVisitRecordsData(filters = {}) {
    return new Promise((resolve) => {
        // 模拟接口延迟 1000ms
        setTimeout(() => {
            // 基础数据
            let records = [
                {
                    id: 1,
                    houseImg: "https://picsum.photos/id/1029/80/80",
                    houseTitle: "幸福家园 一室一厅",
                    housePrice: 2800,
                    visitorName: "李先生",
                    visitorPhone: "138****6789",
                    visitTime: "2023-11-05 10:30",
                    duration: "约30分钟",
                    status: "已成交",
                    statusColor: "green",
                    feedback: "房子整体不错，位置也合适，价格可以接受，希望能尽快签约。",
                    operations: [
                        { type: "contract", text: "查看合同", icon: "fa-file-text-o", color: "bear-brown" }
                    ]
                },
                {
                    id: 2,
                    houseImg: "https://picsum.photos/id/239/80/80",
                    houseTitle: "丽景花园 两室两厅",
                    housePrice: 3600,
                    visitorName: "张女士",
                    visitorPhone: "139****5678",
                    visitTime: "2023-11-03 15:00",
                    duration: "约45分钟",
                    status: "考虑中",
                    statusColor: "yellow",
                    feedback: "房子很满意，但价格有点超出预算，需要再考虑一下，和家人商量后给答复。",
                    operations: [
                        { type: "contact", text: "跟进联系", icon: "fa-phone", color: "blue" }
                    ]
                },
                {
                    id: 3,
                    houseImg: "https://picsum.photos/id/1029/80/80",
                    houseTitle: "幸福家园 一室一厅",
                    housePrice: 2800,
                    visitorName: "王先生",
                    visitorPhone: "136****2345",
                    visitTime: "2023-10-28 09:30",
                    duration: "约20分钟",
                    status: "未成交",
                    statusColor: "red",
                    feedback: "房间有点小，不太符合我的需求，暂时不考虑了，谢谢。",
                    operations: [
                        { type: "view", text: "详情", icon: "fa-eye", color: "gray" }
                    ]
                }
            ];

            // 应用搜索过滤
            if (filters.search) {
                const searchTerm = filters.search.toLowerCase();
                records = records.filter(record =>
                    record.houseTitle.toLowerCase().includes(searchTerm) ||
                    record.visitorName.toLowerCase().includes(searchTerm)
                );
            }

            // 应用房源过滤
            if (filters.houseId) {
                // 在实际应用中，这里会根据房源ID过滤
                // 这里仅做示例，不实际过滤
            }

            resolve({
                records: records,
                totalPages: 1,
                currentPage: 1
            });
        }, 1000);
    });
}

// -------------------------- 异步加载渲染函数 --------------------------
// 1. 加载并渲染预约看房记录
async function loadAppointments() {
    const appointmentsList = document.getElementById('appointmentsList');
    const appointmentsLoading = document.getElementById('appointmentsLoading');
    const appointmentsEmpty = document.getElementById('appointmentsEmpty');

    // 显示加载状态，隐藏列表和空状态
    appointmentsLoading.classList.remove('hidden');
    appointmentsList.innerHTML = '';
    appointmentsEmpty.classList.add('hidden');

    try {
        // 异步获取数据
        const appointmentsData = await getAppointmentsData();

        // 隐藏加载状态
        appointmentsLoading.classList.add('hidden');

        // 处理空数据
        if (appointmentsData.length === 0) {
            appointmentsEmpty.classList.remove('hidden');
            return;
        }

        // 渲染预约记录
        const fragment = document.createDocumentFragment();
        appointmentsData.forEach(item => {
            const tr = document.createElement('tr');
            tr.className = 'border-t border-gray-100';

            // 渲染操作按钮
            let operationHtml = '';
            item.operations.forEach(op => {
                operationHtml += `
                        <button class="text-${op.color}-600 hover:text-${op.color}-800 text-sm mr-3 appointment-operation"
                                data-id="${item.id}" data-operation="${op.type}">
                            <i class="fa ${op.icon} mr-1"></i>${op.text}
                        </button>
                    `;
            });

            tr.innerHTML = `
                    <td class="py-3 px-4">
                        <div class="flex items-center">
                            <img src="${item.houseImg}" alt="${item.houseTitle}" class="w-12 h-12 object-cover rounded mr-3">
                            <div>
                                <p class="font-medium">${item.houseTitle}</p>
                                <p class="text-gray-500 text-sm">¥${item.housePrice}/月</p>
                            </div>
                        </div>
                    </td>
                    <td class="py-3 px-4">${item.visitorName}</td>
                    <td class="py-3 px-4">${item.appointTime}</td>
                    <td class="py-3 px-4">${item.visitorPhone}</td>
                    <td class="py-3 px-4">
                        <span class="bg-${item.statusColor}-100 text-${item.statusColor}-800 text-xs font-medium px-2.5 py-0.5 rounded">${item.status}</span>
                    </td>
                    <td class="py-3 px-4">
                        <div class="flex">${operationHtml}</div>
                    </td>
                `;
            fragment.appendChild(tr);
        });
        appointmentsList.appendChild(fragment);

        // 绑定操作按钮事件
        bindAppointmentOperations();

    } catch (error) {
        // 错误处理
        appointmentsLoading.classList.add('hidden');
        appointmentsList.innerHTML = '<tr><td colspan="6" class="text-center py-6 text-red-500"><i class="fa fa-exclamation-circle mr-1"></i> 加载失败，请重试</td></tr>';
        console.error('预约记录加载失败：', error);
    }
}

// 2. 加载并渲染看房记录
async function loadVisitRecords(filters = {}) {
    const visitRecordsList = document.getElementById('visitRecordsList');
    const visitRecordsLoading = document.getElementById('visitRecordsLoading');
    const visitRecordsEmpty = document.getElementById('visitRecordsEmpty');
    const paginationContainer = document.getElementById('visitRecordsPagination');

    // 显示加载状态，隐藏列表和空状态
    visitRecordsLoading.classList.remove('hidden');
    visitRecordsList.innerHTML = '';
    visitRecordsEmpty.classList.add('hidden');
    paginationContainer.innerHTML = '';

    try {
        // 异步获取数据
        const result = await getVisitRecordsData(filters);
        const visitRecordsData = result.records;

        // 隐藏加载状态
        visitRecordsLoading.classList.add('hidden');

        // 处理空数据
        if (visitRecordsData.length === 0) {
            visitRecordsEmpty.classList.remove('hidden');
            return;
        }

        // 渲染看房记录
        const fragment = document.createDocumentFragment();
        visitRecordsData.forEach(item => {
            const recordItem = document.createElement('div');
            recordItem.className = 'border border-gray-100 rounded-lg p-4 hover:shadow-md transition-shadow duration-200';

            // 渲染操作按钮
            let operationHtml = '';
            item.operations.forEach(op => {
                operationHtml += `
                        <button class="text-${op.color} hover:text-${op.color}-dark text-sm visit-operation"
                                data-id="${item.id}" data-operation="${op.type}">
                            <i class="fa ${op.icon} mr-1"></i>${op.text}
                        </button>
                    `;
            });

            recordItem.innerHTML = `
                    <div class="flex justify-between items-start mb-4">
                        <div class="flex items-center">
                            <img src="${item.houseImg}" alt="${item.houseTitle}" class="w-12 h-12 object-cover rounded mr-3">
                            <div>
                                <h4 class="font-bold text-bear-dark">${item.houseTitle}</h4>
                                <p class="text-gray-500 text-sm">¥${item.housePrice}/月</p>
                            </div>
                        </div>
                        <span class="bg-${item.statusColor}-100 text-${item.statusColor}-800 text-xs font-medium px-2.5 py-0.5 rounded">${item.status}</span>
                    </div>

                    <div class="grid grid-cols-1 md:grid-cols-3 gap-4 mb-4">
                        <div>
                            <p class="text-gray-500 text-sm mb-1">看房人</p>
                            <p class="font-medium">${item.visitorName} (${item.visitorPhone})</p>
                        </div>
                        <div>
                            <p class="text-gray-500 text-sm mb-1">看房时间</p>
                            <p class="font-medium">${item.visitTime}</p>
                        </div>
                        <div>
                            <p class="text-gray-500 text-sm mb-1">看房时长</p>
                            <p class="font-medium">${item.duration}</p>
                        </div>
                    </div>

                    <div class="mb-4">
                        <p class="text-gray-500 text-sm mb-1">客户反馈</p>
                        <p class="bg-gray-50 p-3 rounded border border-gray-100">${item.feedback}</p>
                    </div>

                    <div class="flex justify-end">
                        ${operationHtml}
                    </div>
                `;
            fragment.appendChild(recordItem);
        });
        visitRecordsList.appendChild(fragment);

        // 渲染分页
        renderPagination(paginationContainer, result.currentPage, result.totalPages);

        // 绑定操作按钮事件
        bindVisitOperations();

    } catch (error) {
        // 错误处理
        visitRecordsLoading.classList.add('hidden');
        visitRecordsList.innerHTML = '<div class="text-center py-6 text-red-500"><i class="fa fa-exclamation-circle mr-1"></i> 加载失败，请重试</div>';
        console.error('看房记录加载失败：', error);
    }
}

// 渲染分页控件
function renderPagination(container, currentPage, totalPages) {
    if (totalPages <= 1) return;

    let paginationHtml = `
            <nav class="inline-flex rounded-md shadow">
                <a href="#" class="relative inline-flex items-center px-2 py-2 rounded-l-md border border-gray-300 bg-white text-sm font-medium text-gray-500 hover:bg-gray-50 page-prev" data-page="${currentPage - 1}">
                    <i class="fa fa-chevron-left"></i>
                </a>
        `;

    for (let i = 1; i <= totalPages; i++) {
        paginationHtml += `
                <a href="#" class="relative inline-flex items-center px-4 py-2 border text-sm font-medium page-link ${i === currentPage ? 'z-10 bg-bear-brown text-white border-bear-brown' : 'bg-white border-gray-300 text-gray-500 hover:bg-gray-50'}" data-page="${i}">
                    ${i}
                </a>
            `;
    }

    paginationHtml += `
                <a href="#" class="relative inline-flex items-center px-2 py-2 rounded-r-md border border-gray-300 bg-white text-sm font-medium text-gray-500 hover:bg-gray-50 page-next" data-page="${currentPage + 1}">
                    <i class="fa fa-chevron-right"></i>
                </a>
            </nav>
        `;

    container.innerHTML = paginationHtml;

    // 绑定分页事件
    bindPaginationEvents(container);
}

// 绑定分页事件
function bindPaginationEvents(container) {
    const pageLinks = container.querySelectorAll('.page-link, .page-prev, .page-next');
    pageLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            const page = parseInt(this.getAttribute('data-page'));
            if (page > 0 && page <= parseInt(this.closest('nav').querySelectorAll('.page-link').length)) {
                // 获取当前筛选条件
                const filters = getVisitRecordsFilters();
                // 加载指定页数据
                loadVisitRecords({...filters, page: page});
            }
        });
    });
}

// 获取看房记录筛选条件
function getVisitRecordsFilters() {
    return {
        search: document.getElementById('visitRecordSearch').value,
        houseId: document.getElementById('houseFilter').value,
        dateRange: document.getElementById('dateFilter').value
    };
}

// 绑定预约操作事件
function bindAppointmentOperations() {
    const operations = document.querySelectorAll('.appointment-operation');
    operations.forEach(btn => {
        btn.addEventListener('click', function() {
            const id = this.getAttribute('data-id');
            const operation = this.getAttribute('data-operation');

            if (operation === 'confirm') {
                if (confirm('确定要确认此预约吗？')) {
                    // 模拟确认操作
                    simulateAppointmentOperation(id, operation);
                }
            } else if (operation === 'cancel') {
                if (confirm('确定要取消此预约吗？')) {
                    // 模拟取消操作
                    simulateAppointmentOperation(id, operation);
                }
            } else if (operation === 'view') {
                // 查看详情
                alert(`查看预约 #${id} 的详情`);
            }
        });
    });
}

// 绑定看房记录操作事件
function bindVisitOperations() {
    const operations = document.querySelectorAll('.visit-operation');
    operations.forEach(btn => {
        btn.addEventListener('click', function() {
            const id = this.getAttribute('data-id');
            const operation = this.getAttribute('data-operation');

            if (operation === 'contract') {
                alert(`查看合同 #${id}`);
            } else if (operation === 'contact') {
                alert(`联系客户 #${id}`);
            } else if (operation === 'view') {
                alert(`查看详情 #${id}`);
            }
        });
    });
}

// 模拟预约操作
function simulateAppointmentOperation(id, operation) {
    // 显示加载状态
    const appointmentsLoading = document.getElementById('appointmentsLoading');
    appointmentsLoading.classList.remove('hidden');

    // 模拟API请求延迟
    setTimeout(() => {
        // 隐藏加载状态
        appointmentsLoading.classList.add('hidden');

        // 刷新列表
        loadAppointments();

        // 显示操作结果
        if (operation === 'confirm') {
            showNotification('预约已确认', 'success');
        } else if (operation === 'cancel') {
            showNotification('预约已取消', 'info');
        }
    }, 600);
}

// 显示通知
function showNotification(message, type = 'info') {
    // 创建通知元素
    const notification = document.createElement('div');
    notification.className = `fixed top-4 right-4 px-4 py-3 rounded-lg shadow-lg z-50 flex items-center transition-all duration-300 transform translate-x-full`;

    // 设置通知样式
    if (type === 'success') {
        notification.classList.add('bg-green-500', 'text-white');
        notification.innerHTML = `<i class="fa fa-check-circle mr-2"></i>${message}`;
    } else if (type === 'error') {
        notification.classList.add('bg-red-500', 'text-white');
        notification.innerHTML = `<i class="fa fa-exclamation-circle mr-2"></i>${message}`;
    } else {
        notification.classList.add('bg-blue-500', 'text-white');
        notification.innerHTML = `<i class="fa fa-info-circle mr-2"></i>${message}`;
    }

    // 添加到页面
    document.body.appendChild(notification);

    // 显示通知
    setTimeout(() => {
        notification.classList.remove('translate-x-full');
        notification.classList.add('translate-x-0');
    }, 100);

    // 3秒后隐藏通知
    setTimeout(() => {
        notification.classList.remove('translate-x-0');
        notification.classList.add('translate-x-full');

        // 移除元素
        setTimeout(() => {
            document.body.removeChild(notification);
        }, 300);
    }, 3000);
}

// -------------------------- 原有功能保留并改造 --------------------------


// 功能模块切换（改造：切换到预约/看房记录模块时自动异步加载）
function setupFunctionTabs() {
    const buttons = document.querySelectorAll('.function-btn');
    const contents = document.querySelectorAll('.function-content');
    let loadedModules = new Set(); // 记录已加载过的模块，避免重复请求

    buttons.forEach(button => {
        button.addEventListener('click', () => {
            // 移除所有按钮的活跃状态
            buttons.forEach(btn => {
                btn.classList.remove('active', 'border-bear-brown', 'bg-bear-cream/50');
                btn.classList.add('border-gray-200');
            });

            // 添加当前按钮的活跃状态
            button.classList.add('active', 'border-bear-brown', 'bg-bear-cream/50');
            button.classList.remove('border-gray-200');

            // 隐藏所有内容
            contents.forEach(content => {
                content.classList.add('hidden');
                content.classList.remove('active');
            });

            // 显示对应内容并加载数据
            const target = button.getAttribute('data-target');
            const activeContent = document.getElementById(target);
            activeContent.classList.remove('hidden');
            activeContent.classList.add('active');

            // 切换到预约看房模块：首次加载或刷新时异步加载
            if (target === 'appointments' && (!loadedModules.has('appointments') || event.target.id === 'refreshAppointments')) {
                loadAppointments();
                loadedModules.add('appointments');
            }

            // 切换到看房记录模块：首次加载或刷新时异步加载
            if (target === 'visit-records' && (!loadedModules.has('visit-records') || event.target.id === 'refreshVisitRecords')) {
                loadVisitRecords();
                loadedModules.add('visit-records');
            }
        });
    });

    // 预约记录刷新按钮事件
    document.getElementById('refreshAppointments').addEventListener('click', () => {
        loadAppointments();
    });

    // 看房记录刷新按钮事件
    document.getElementById('refreshVisitRecords').addEventListener('click', () => {
        loadVisitRecords();
    });

    // 搜索和筛选事件
    document.getElementById('visitRecordSearch').addEventListener('input', debounce(function() {
        loadVisitRecords(getVisitRecordsFilters());
    }, 500));

    document.getElementById('houseFilter').addEventListener('change', function() {
        loadVisitRecords(getVisitRecordsFilters());
    });

    document.getElementById('dateFilter').addEventListener('change', function() {
        loadVisitRecords(getVisitRecordsFilters());
    });
}

// 防抖函数
function debounce(func, wait) {
    let timeout;
    return function() {
        const context = this;
        const args = arguments;
        clearTimeout(timeout);
        timeout = setTimeout(() => func.apply(context, args), wait);
    };
}

// 房源表单提交
function setupHouseForm() {
    const form = document.getElementById('houseForm');
    if (form) {
        form.addEventListener('submit', function(e) {
            e.preventDefault();
            alert('房源发布成功，将进入审核阶段！');
            form.reset();
        });
    }
}

// 检查登录状态
function checkLoginStatus() {
    // 模拟登录状态：实际项目中从localStorage或后端获取
    const mockLandlord = localStorage.getItem('currentLandlord')
        ? JSON.parse(localStorage.getItem('currentLandlord'))
        : { id: 'FD20230315', name: '张房东', username: 'zhangfd' };
    // 若未登录，跳转登录页
    if (!mockLandlord) {
        window.location.href = 'login.html';
        return null;
    }
    return mockLandlord;
}

// 页面加载时初始化
document.addEventListener('DOMContentLoaded', function() {
    // 检查登录状态
    const landlord = checkLoginStatus();
    if (landlord) {
        // 显示用户名
        document.getElementById('userName').textContent = landlord.name || landlord.username;
        document.getElementById('centerUserName').textContent = landlord.name || landlord.username;
        document.getElementById('userId').textContent = landlord.id;
    }

    // 初始化功能模块切换（含异步加载）
    setupFunctionTabs();

    // 初始化房源表单
    setupHouseForm();

    // 页面加载完成后，若默认显示预约/看房记录模块，自动加载数据
    const activeTab = document.querySelector('.function-btn.active');
    if (activeTab && activeTab.getAttribute('data-target') === 'appointments') {
        loadAppointments();
    }
    if (activeTab && activeTab.getAttribute('data-target') === 'visit-records') {
        loadVisitRecords();
    }
});