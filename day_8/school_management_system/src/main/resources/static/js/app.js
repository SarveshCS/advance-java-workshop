document.querySelectorAll('[data-picker]').forEach((picker) => {
    const input = picker.querySelector('[data-picker-input]');
    const hidden = picker.querySelector('[data-picker-hidden]');
    const datalist = picker.querySelector('datalist');

    if (!input || !hidden || !datalist) {
        return;
    }

    const syncValue = () => {
        const option = Array.from(datalist.options).find((item) => item.value === input.value);
        hidden.value = option ? option.dataset.id : '';
    };

    input.addEventListener('input', syncValue);
    input.addEventListener('change', syncValue);
    syncValue();
});