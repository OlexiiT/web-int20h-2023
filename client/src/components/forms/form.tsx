import clsx from "clsx";

type Props = {
  children: React.ReactNode;
  captionText?: string;
  className?: string;
};

export default function Form({ children, captionText, className }: Props) {
  return (
    <form className={clsx("flex flex-col gap-4", className)}>
      {captionText && <FormCaption text={captionText} className="mb-4" />}
      {children}
    </form>
  );
}

type FormCaptionProps = {
  text: string;
  className?: string;
};

function FormCaption({ text, className }: FormCaptionProps) {
    return <h1 className={clsx(className, "text-3xl")}>{text}</h1>  
}
